package ch.supsi.imageEditor.backend.application.pipeline;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PipelineControllerTest {
    @BeforeEach
    public void beforeEach() {
        PipelineController.instance = null;
    }

    @Test
    public void constructor() {
        PipelineController pipelineController = new PipelineController();
        Assertions.assertNotNull(pipelineController);
        Assertions.assertNotNull(pipelineController.getImageModel());
        Assertions.assertNotNull(pipelineController.getPipelineModel());
        Assertions.assertNotNull(pipelineController.getOperationModel());
        Assertions.assertNotNull(pipelineController.getNotificationService());
    }

    @Test
    public void instance() {
        PipelineController pipelineController = PipelineController.getInstance();
        Assertions.assertNotNull(pipelineController);
        Assertions.assertNotNull(PipelineController.instance);
        Assertions.assertNotNull(pipelineController.getImageModel());
        Assertions.assertNotNull(pipelineController.getPipelineModel());
        Assertions.assertNotNull(pipelineController.getOperationModel());
        Assertions.assertNotNull(pipelineController.getNotificationService());
    }

    @Test
    public void checkSingleton() {
        PipelineController pipelineController1 = PipelineController.getInstance();
        PipelineController pipelineController2 = PipelineController.getInstance();
        Assertions.assertEquals(pipelineController1, pipelineController2);
    }
}