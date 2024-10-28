package ch.supsi.imageEditor.backend.business.images;

import ch.supsi.imageEditor.backend.dataaccess.images.ImageDataAccess;
import ch.supsi.imageEditor.backend.dataaccess.images.ImageDataAccessInterface;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ImageFactory implements ImageFactoryInterface {
    private static ImageFactory instance = null;
    private final ImageDataAccessInterface imageReaderDataAccess;

    private final Map<String, ImageReaderInterface> imageReaders;
    private final Properties imageReaderProperties;
    private ImageReaderInterface currentImageReader;

    private ImageFactory() {
        this.imageReaderDataAccess = ImageDataAccess.getInstance();
        this.imageReaderProperties = this.imageReaderDataAccess.getFormatReaderProperties();

        this.imageReaders = this.loadImageReadersMap();
    }

    public static ImageFactory getInstance() {
        return instance == null ? instance = new ImageFactory() : instance;
    }

    private Map<String, ImageReaderInterface> loadImageReadersMap() {
        Map<String, ImageReaderInterface> imageReadersMap = new HashMap<>();
        for (String extension : this.imageReaderProperties.stringPropertyNames()) {
            String readerClassName = this.imageReaderProperties.getProperty(extension);
            try {
                Class<?> readerClass = Class.forName(readerClassName);
                ImageReaderInterface imageReader = (ImageReaderInterface) readerClass.getConstructor().newInstance();
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
    }

    private String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filePath.length() - 1)
            return "";
        return filePath.substring(lastDotIndex + 1);
    }

    @Override
    public Set<String> getSupportedFormat() {
        return Set.copyOf(this.imageReaders.keySet());
    }

    @Override
    public AbstractImage getImage() {
        return this.currentImageReader.getImage();
    }

    @Override
    public void writeImage(AbstractImage image, File file) {
        this.imageReaderDataAccess.writeImage(image, file);
    }
}
