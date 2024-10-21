package ch.supsi.imageEditor.backend.application.pipeline;

public class PipelineController implements PipelineControllerInterface{
    private static PipelineController instance = null;
    //private final NotificationServiceInterface notificationService;

    private PipelineController() {
        this.pipelineModel = PipelineModel.getInstance();
        //this.notificationService = NotificationService.getInstance();
    }

    public static PipelineController getInstance() {
        return instance == null ? instance = new PipelineController() : instance;
    }
}
