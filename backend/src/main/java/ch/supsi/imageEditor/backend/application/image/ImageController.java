package ch.supsi.imageEditor.backend.application.image;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.application.observer.NotificationService;
import ch.supsi.imageEditor.backend.application.observer.NotificationServiceInterface;
import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import ch.supsi.imageEditor.backend.business.images.ImageFactoryInterface;
import ch.supsi.imageEditor.backend.business.pipeline.PipelineModel;
import ch.supsi.imageEditor.backend.business.pipeline.PipelineModelInterface;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ImageController implements ImageControllerInterface {
    protected static ImageController instance = null;

    private final ImageFactoryInterface imageReaderFactory;
    private final PipelineModelInterface pipelineModel;
    private final NotificationServiceInterface notificationService;

    protected ImageController() {
        this.imageReaderFactory = ImageFactory.getInstance();
        this.pipelineModel = PipelineModel.getInstance();
        this.notificationService = NotificationService.getInstance();
    }

    public static ImageController getInstance() {
        return instance == null ? instance = new ImageController() : instance;
    }

    @Override
    public void readImage(String filePath) throws FormatNotSupportedException, IOException, ImageHeaderUncorrectException {
        this.imageReaderFactory.readImage(filePath);
        this.notificationService.notify(EventType.OPEN_IMAGE);
        if (!this.pipelineModel.getPipeline().isEmpty()) {
            this.pipelineModel.cleanPipeline();
            this.notificationService.notify(EventType.CLEAR_PIPELINE);
        }
    }

    @Override
    public void writeImage(File file) {
        this.imageReaderFactory.writeImage(this.imageReaderFactory.getImage(), file);
        this.notificationService.notify(EventType.SAVE_IMAGE);
    }

    @Override
    public AbstractImage getImage() {
        return this.imageReaderFactory.getImage();
    }

    @Override
    public Set<String> getSupportedFormat() {
        return this.imageReaderFactory.getSupportedFormat();
    }

    @Override
    public List<String> getRecentFiles() {
        return this.imageReaderFactory.getRecentFiles();
    }

    @Override
    public void closeImage() {
        if (!this.pipelineModel.getPipeline().isEmpty()) {
            this.pipelineModel.cleanPipeline();
            this.notificationService.notify(EventType.CLEAR_PIPELINE);
        }
        this.imageReaderFactory.closeImage();
        this.notificationService.notify(EventType.CLOSE_IMAGE);
    }
}
