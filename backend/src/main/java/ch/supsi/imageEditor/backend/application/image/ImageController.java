package ch.supsi.imageEditor.backend.application.image;

import ch.supsi.imageEditor.backend.business.images.ImageReaderFactory;
import ch.supsi.imageEditor.backend.business.images.ImageReaderFactoryInterface;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;

public class ImageController implements ImageControllerInterface {
    private static ImageController instance = null;

    private final ImageReaderFactoryInterface imageReaderFactory;

    private ImageController() {
        this.imageReaderFactory = ImageReaderFactory.getInstance();

        this.readImage("");
    }

    public static ImageController getInstance() {
        return instance == null ? instance = new ImageController() : instance;
    }

    public void readImage(String path) {
        path = "jpeg";
        try {
            this.imageReaderFactory.readImage(path);
        } catch (FormatNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
}
