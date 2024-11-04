package ch.supsi.imageEditor.backend.business.images;

import ch.supsi.imageEditor.backend.dataaccess.images.ImageDataAccess;
import ch.supsi.imageEditor.backend.dataaccess.images.ImageDataAccessInterface;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ImageFactory implements ImageFactoryInterface {
    private static ImageFactory instance = null;
    private final ImageDataAccessInterface imageDataAccess;

    private final List<String> recentFilesList;
    private final Map<String, ImageInterface> imageReaders;
    private final Properties imageReaderProperties;
    private ImageInterface currentImageReader;
    private AbstractImage currentImage;

    private ImageFactory() {
        this.imageDataAccess = ImageDataAccess.getInstance();
        this.imageReaderProperties = this.imageDataAccess.getFormatReaderProperties();

        this.recentFilesList = this.imageDataAccess.getRecentFiles();
        this.imageReaders = this.loadImageReadersMap();
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

    private String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filePath.length() - 1)
            return "";
        return filePath.substring(lastDotIndex + 1);
    }

    private void persistRecentFile(String filePath) {
        if(!filePath.contains("test")){
            this.recentFilesList.remove(filePath);
            this.recentFilesList.add(0, filePath);
            this.imageDataAccess.persistRecentFile(this.recentFilesList);
        }
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
    public void closeImage() {
        this.currentImage = null;
        this.currentImageReader = null;
    }
}
