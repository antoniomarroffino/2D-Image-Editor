package ch.supsi.imageEditor.backend.application.image;

import ch.supsi.imageEditor.backend.business.images.ImageReaderFactory;
import ch.supsi.imageEditor.backend.business.images.ImageReaderFactoryInterface;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;

import java.util.Set;

public class ImageController implements ImageControllerInterface {
    private static ImageController instance = null;

    private final ImageReaderFactoryInterface imageReaderFactory;

    private ImageController() {
        this.imageReaderFactory = ImageReaderFactory.getInstance();
    }

    public static ImageController getInstance() {
        return instance == null ? instance = new ImageController() : instance;
    }

    @Override
    public void displayImage(String fileName) {
        try {
            this.imageReaderFactory.readImage(this.getFileExtension(fileName));
        } catch (FormatNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Set<String> getSupportedFormat() {
        return this.imageReaderFactory.getSupportedFormat();
    }

    private String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filePath.length() - 1) {
            return "";
        }
        return filePath.substring(lastDotIndex + 1);
    }
}
