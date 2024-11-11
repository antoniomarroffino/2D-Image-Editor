package ch.supsi.imageEditor.backend.application.pipeline;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.application.observer.NotificationService;
import ch.supsi.imageEditor.backend.application.observer.NotificationServiceInterface;
import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import ch.supsi.imageEditor.backend.business.images.ImageFactoryInterface;
import ch.supsi.imageEditor.backend.business.operation.OperationModel;
import ch.supsi.imageEditor.backend.business.pipeline.PipelineModel;
import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;

public class PipelineController implements PipelineControllerInterface {
    protected static PipelineController instance = null;

    private final PipelineModel pipelineModel;
    private final OperationModel operationModel;
    private final ImageFactoryInterface imageModel;
    private final NotificationServiceInterface notificationService;

    protected PipelineController() {
        this.pipelineModel = PipelineModel.getInstance();
        this.operationModel = OperationModel.getInstance();
        this.notificationService = NotificationService.getInstance();
        this.imageModel = ImageFactory.getInstance();
    }

    public static PipelineController getInstance() {
        return instance == null ? instance = new PipelineController() : instance;
    }

    PipelineModel getPipelineModel() {
        return this.pipelineModel;
    }

    OperationModel getOperationModel() {
        return this.operationModel;
    }
    ImageFactoryInterface getImageModel() {
        return this.imageModel;
    }
    NotificationServiceInterface getNotificationService() {
        return this.notificationService;
    }

    @Override
    public void addOperationToPipeline(String name) throws OperationNotSupportedException {
        this.operationModel.checkOperationExists(name);
        this.pipelineModel.addOperationToPipeline(name);
        this.notificationService.notify(EventType.ADDED_OPERATION);
    }

    @Override
    public String getLastOperationAdded() {
        return this.pipelineModel.getLastOperationAdded();
    }

    @Override
    public void deletePipeline() {
        this.pipelineModel.cleanPipeline();
        this.notificationService.notify(EventType.CLEAR_PIPELINE);
    }

    @Override
    public void runPipeline() {
        AbstractImage imageAfterOperations = this.operationModel.executeOperations(this.pipelineModel.getPipeline(), this.imageModel.getImage());
        this.imageModel.setImage(imageAfterOperations);
        this.notificationService.notify(EventType.RUN_PIPELINE);
        this.pipelineModel.cleanPipeline();
        this.notificationService.notify(EventType.CLEAR_PIPELINE);
    }
}
