package ch.supsi.imageEditor.frontend.model.pipeline;

import ch.supsi.imageEditor.backend.application.pipeline.PipelineController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class PipelineModelTest {
    private PipelineModel pipelineModel;

    @BeforeEach
    public void beforeEach() {
        PipelineModel.instance = null;
    }

    @Test
    public void constructor() {
        this.pipelineModel = new PipelineModel();
        Assertions.assertNotNull(this.pipelineModel);
        Assertions.assertNotNull(this.pipelineModel.getPipelineController());
    }

    @Test
    public void instance() {
        this.pipelineModel = PipelineModel.getInstance();
        Assertions.assertNotNull(this.pipelineModel);
        Assertions.assertNotNull(PipelineModel.instance);
        Assertions.assertNotNull(this.pipelineModel.getPipelineController());
    }

    @Test
    public void checkSingleton() {
        PipelineModel pipelineModel1 = PipelineModel.getInstance();
        PipelineModel pipelineModel2 = PipelineModel.getInstance();
        Assertions.assertEquals(pipelineModel1, pipelineModel2);
    }

    @Test
    void getAddedOperation() {
        String expectedOperation = "rotate";
        PipelineController mockPipelineController = Mockito.mock(PipelineController.class);
        try (MockedStatic<PipelineController> pipelineControllerStaticMock = Mockito.mockStatic(PipelineController.class)) {
            pipelineControllerStaticMock.when(PipelineController::getInstance).thenReturn(mockPipelineController);
            when(mockPipelineController.getLastOperationAdded()).thenReturn(expectedOperation);
            pipelineModel = PipelineModel.getInstance();
            String actualOperation = pipelineModel.getAddedOperation();
            Assertions.assertEquals(expectedOperation, actualOperation);
        }
    }

    @Test
    void deletePipeline() {
        PipelineController mockPipelineController = Mockito.mock(PipelineController.class);
        try (MockedStatic<PipelineController> pipelineControllerStaticMock = Mockito.mockStatic(PipelineController.class)) {
            pipelineControllerStaticMock.when(PipelineController::getInstance).thenReturn((PipelineController) mockPipelineController);
            pipelineModel = PipelineModel.getInstance();
            pipelineModel.deletePipeline();
            verify(mockPipelineController, times(1)).deletePipeline();
        }
    }

    @Test
    void runPipeline() {
        PipelineController mockPipelineController = Mockito.mock(PipelineController.class);
        try (MockedStatic<PipelineController> pipelineControllerStaticMock = mockStatic(PipelineController.class)) {
            pipelineControllerStaticMock.when(PipelineController::getInstance).thenReturn((PipelineController) mockPipelineController);
            pipelineModel = PipelineModel.getInstance();
            pipelineModel.runPipeline();
            verify(mockPipelineController, times(1)).runPipeline();
        }
    }
}