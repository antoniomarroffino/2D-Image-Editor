package ch.supsi.imageEditor.backend.application.image;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.application.observer.NotificationService;
import ch.supsi.imageEditor.backend.application.observer.NotificationServiceInterface;
import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import ch.supsi.imageEditor.backend.business.images.ImageFactoryInterface;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class ImageController implements ImageControllerInterface {
    private static ImageController instance = null;

    private final ImageFactoryInterface imageReaderFactory;
    private final NotificationServiceInterface notificationService;

    private ImageController() {
        this.imageReaderFactory = ImageFactory.getInstance();
        this.notificationService = NotificationService.getInstance();
    }

    public static ImageController getInstance() {
        return instance == null ? instance = new ImageController() : instance;
    }

    @Override
    public void loadImage(String filePath) throws FormatNotSupportedException, IOException, ImageHeaderUncorrectException {
        this.imageReaderFactory.readImage(filePath);
        this.notificationService.notify(EventType.OPEN_IMAGE);
    }

    @Override
    public void writeImage(File file) {
        this.imageReaderFactory.writeImage(this.imageReaderFactory.getImage(), file);
    }

    @Override
    public AbstractImage getImage() {
        return this.imageReaderFactory.getImage();
    }

    @Override
    public Set<String> getSupportedFormat() {
        return this.imageReaderFactory.getSupportedFormat();
    }
}
