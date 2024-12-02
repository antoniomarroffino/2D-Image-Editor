package ch.supsi.imageEditor.backend.business.images;

import ch.supsi.imageEditor.backend.business.images.export.ExportStrategy;
import ch.supsi.imageEditor.backend.dataaccess.images.ImageDataAccess;
import ch.supsi.imageEditor.backend.dataaccess.images.ImageDataAccessInterface;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ImageFactory implements ImageFactoryInterface {
    private static ImageFactory instance = null;
    private final ImageDataAccessInterface imageDataAccess;

    private final List<String> recentFilesList;
    protected final Map<String, ImageInterface> imageReaders;
    protected final Map<String, ArrayList<ExportStrategy>> exportStrategies;
    protected final Properties imageReaderProperties;
    protected final Properties imageExporterProperties;
    protected ImageInterface currentImageReader;
    protected AbstractImage currentImage;

    protected ImageFactory() {
        this.imageDataAccess = ImageDataAccess.getInstance();
        this.imageReaderProperties = this.imageDataAccess.getFormatReaderProperties();
        this.imageExporterProperties = this.imageDataAccess.getFormatExporterProperties();

        this.recentFilesList = this.imageDataAccess.getRecentFiles();
        this.imageReaders = this.loadImageReadersMap();
        this.exportStrategies = this.loadImageExportersMap();
        this.currentImage = null;
    }

    public static ImageFactory getInstance() {
        return instance == null ? instance = new ImageFactory() : instance;
    }

    private Map<String, ImageInterface> loadImageReadersMap() {
        Map<String, ImageInterface> imageReadersMap = new HashMap<>();
        for (String extension : this.imageReaderProperties.stringPropertyNames()) {
            String readerClassName = this.imageReaderProperties.getProperty(extension);
            try {
                Class<?> readerClass = Class.forName(readerClassName);
                ImageInterface imageReader = (ImageInterface) readerClass.getConstructor().newInstance();
                imageReadersMap.put(extension, imageReader);
            } catch (Exception e) {
                throw new RuntimeException("Error during load of image reader: " + extension);
            }
        }
        return imageReadersMap;
    }

    private Map<String, ArrayList<ExportStrategy>> loadImageExportersMap() {
        Map<String, ArrayList<ExportStrategy>> exportStrategyMap = new HashMap<>();
        for (String extension : this.imageExporterProperties.stringPropertyNames()) {
            String exporterClassName = this.imageExporterProperties.getProperty(extension);
            String[] classPaths = exporterClassName.split(",");
            ArrayList<ExportStrategy> strategies = new ArrayList<>();

            for (String classPath : classPaths) {
                try {
                    Class<?> exporterClass = Class.forName(classPath.trim());
                    ExportStrategy instance = (ExportStrategy) exporterClass.getDeclaredConstructor().newInstance();
                    strategies.add(instance);
                } catch (Exception e) {
                    throw new RuntimeException("Error during load of image exporter: " + extension);
                }
            }

            exportStrategyMap.put(extension, strategies);
        }
        return exportStrategyMap;
    }

    @Override
    public void readImage(String filePath) throws FormatNotSupportedException, IOException, ImageHeaderUncorrectException {
        String extension = this.getFileExtension(filePath).toUpperCase();
        this.currentImageReader = this.imageReaders.get(extension);
        if (this.currentImageReader == null)
            throw new FormatNotSupportedException("Format " + extension + " is not supported");
        this.currentImageReader.read(filePath);
        this.currentImage = this.currentImageReader.getImage();
        this.persistRecentFile(filePath);
    }

    protected String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filePath.length() - 1)
            return "";
        return filePath.substring(lastDotIndex + 1);
    }

    private void persistRecentFile(String filePath) {
        this.recentFilesList.remove(filePath);
        this.recentFilesList.add(0, filePath);
        this.imageDataAccess.persistRecentFile(this.recentFilesList);
    }

    @Override
    public Set<String> getSupportedFormat() {
        return Set.copyOf(this.imageReaders.keySet());
    }

    @Override
    public List<String> getRecentFiles() {
        return List.copyOf(this.recentFilesList);
    }

    @Override
    public AbstractImage getImage() {
        return this.currentImage;
    }

    @Override
    public void setImage(AbstractImage image) {
        this.currentImage = image;
    }

    @Override
    public void writeImage(AbstractImage image, File file) {
        this.imageDataAccess.writeImage(image, file);
    }

    @Override
    public void writeImage(AbstractImage image, File sourceFile, File destinationFile) {
        String sourceExtension = this.getFileExtension(sourceFile.getName()).toUpperCase();
        String destinationExtension = this.getFileExtension(destinationFile.getName()).toUpperCase();

        if (sourceExtension.equals(destinationExtension))
            this.writeImage(image, destinationFile);
        else {
            AbstractImage exportedImage = exportPath(image, sourceFile, destinationFile, "up");
            if (exportedImage == null)
                exportedImage = exportPath(image, sourceFile, destinationFile, "down");
            if (exportedImage != null)
                this.writeImage(exportedImage, destinationFile);
        }
    }

    private AbstractImage exportPath(AbstractImage image, File sourceFile, File destinationFile, String direction) {
        String sourceExtension = this.getFileExtension(sourceFile.getName()).toUpperCase();
        String destinationExtension = this.getFileExtension(destinationFile.getName()).toUpperCase();
        Queue<List<AbstractImage>> queue = new LinkedList<>();
        Set<AbstractImage> visited = new HashSet<>();
        queue.add(List.of(imageReaders.get(sourceExtension).getImage()));
        visited.add(imageReaders.get(sourceExtension).getImage());

        while (!queue.isEmpty()) {
            List<AbstractImage> path = queue.poll();
            AbstractImage current = path.get(path.size() - 1);

            if (current.equals(imageReaders.get(destinationExtension).getImage()))
                return processExportPath(image, path, direction);

            List<AbstractImage> neighbors = new ArrayList<>();
            List<String> exportList = direction.equals("up") ? current.getUpExport() : current.getDownExport();
            if (exportList != null)
                for (String s : exportList)
                    neighbors.add(imageReaders.get(s).getImage());
            for (AbstractImage neighbor : neighbors)
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<AbstractImage> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
        }
        return null;
    }

    private AbstractImage processExportPath(AbstractImage image, List<AbstractImage> path, String direction) {
        for (int i = 0; i < path.size() - 1; i++)
            try {
                ExportStrategy exportStrategy = exportStrategies.get(path.get(i).getFormat()).get(0);
                Method method = exportStrategy.getClass().getDeclaredMethod(direction, AbstractImage.class);
                ExportStrategy clazz = exportStrategy.getClass().getDeclaredConstructor().newInstance();
                image = (AbstractImage) method.invoke(clazz, image);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored) {
                ;
            }
        return image;
    }

    @Override
    public void closeImage() {
        this.currentImage = null;
        this.currentImageReader = null;
    }
}
