package ch.supsi.imageEditor.backend.application.pipeline;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.application.observer.NotificationService;
import ch.supsi.imageEditor.backend.application.observer.NotificationServiceInterface;
import ch.supsi.imageEditor.backend.business.images.ImageReaderFactory;
import ch.supsi.imageEditor.backend.business.images.ImageReaderFactoryInterface;
import ch.supsi.imageEditor.backend.business.operation.OperationModel;
import ch.supsi.imageEditor.backend.business.pipeline.PipelineModel;
import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;

public class PipelineController implements PipelineControllerInterface{
    private static PipelineController instance = null;
    private final PipelineModel pipelineModel;
    private final OperationModel operationModel;
    private final ImageReaderFactoryInterface imageReaderModel;
    private final NotificationServiceInterface notificationService;

    private PipelineController() {
        this.pipelineModel = PipelineModel.getInstance();
        this.operationModel = OperationModel.getInstance();
        this.notificationService = NotificationService.getInstance();
        this.imageReaderModel = ImageReaderFactory.getInstance();
    }

    public static PipelineController getInstance() {
        return instance == null ? instance = new PipelineController() : instance;
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
        this.operationModel.executeOperations(this.pipelineModel.getPipeline(), this.imageReaderModel.getImage());
        this.notificationService.notify(EventType.RUN_PIPELINE);
        this.pipelineModel.cleanPipeline();
        this.notificationService.notify(EventType.CLEAR_PIPELINE);
    }
}
