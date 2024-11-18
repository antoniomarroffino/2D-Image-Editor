package ch.supsi.imageEditor.frontend.model.operation;

import ch.supsi.imageEditor.backend.application.pipeline.PipelineController;
import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class OperationModelTest {
    private OperationModel operationModel;

    @BeforeEach
    public void beforeEach() {
        OperationModel.instance = null;
    }

    @Test
    public void constructor() {
        this.operationModel = new OperationModel();
        Assertions.assertNotNull(this.operationModel);
        Assertions.assertNotNull(this.operationModel.getOperationController());
        Assertions.assertNotNull(this.operationModel.getPipelineController());
        Assertions.assertNotNull(this.operationModel.getOperationsTag());
    }

    @Test
    public void instance() {
        this.operationModel = OperationModel.getInstance();
        Assertions.assertNotNull(this.operationModel);
        Assertions.assertNotNull(OperationModel.instance);
        Assertions.assertNotNull(this.operationModel.getOperationController());
        Assertions.assertNotNull(this.operationModel.getPipelineController());
        Assertions.assertNotNull(this.operationModel.getOperationsTag());
    }

    @Test
    public void checkSingleton() {
        OperationModel operationModel1 = OperationModel.getInstance();
        OperationModel operationModel2 = OperationModel.getInstance();
        Assertions.assertEquals(operationModel1, operationModel2);
    }

    @Test
    void addOperationToPipeline() throws OperationNotSupportedException {
        PipelineController mockPipelineController = Mockito.mock(PipelineController.class);
        try (MockedStatic<PipelineController> pipelineControllerStaticMock = Mockito.mockStatic(PipelineController.class)) {
            pipelineControllerStaticMock.when(PipelineController::getInstance).thenReturn(mockPipelineController);
            this.operationModel = OperationModel.getInstance();
            String operationName = "SomeOperation";
            this.operationModel.addOperationToPipeline(operationName);
            verify(mockPipelineController, times(1)).addOperationToPipeline(operationName);
        }
    }

    @Test
    void addOperationToPipeline_throwsException() throws OperationNotSupportedException {
        PipelineController mockPipelineController = mock(PipelineController.class);
        try (MockedStatic<PipelineController> pipelineControllerStaticMock = mockStatic(PipelineController.class)) {
            pipelineControllerStaticMock.when(PipelineController::getInstance).thenReturn(mockPipelineController);
            this.operationModel = OperationModel.getInstance();
            String invalidOperation = "InvalidOperation";
            doThrow(OperationNotSupportedException.class).when(mockPipelineController).addOperationToPipeline(invalidOperation);
            Assertions.assertThrows(OperationNotSupportedException.class, () -> this.operationModel.addOperationToPipeline(invalidOperation));
        }
    }
}