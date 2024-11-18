package ch.supsi.imageEditor.frontend.controller.operation;

import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.operation.OperationModel;
import ch.supsi.imageEditor.frontend.view.popup.error.ErrorViewPopUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class OperationControllerTest {
    private OperationController operationController;

    @BeforeEach
    public void beforeEach() {
        OperationController.instance = null;
    }

    @Test
    public void constructor() {
        operationController = new OperationController();
        Assertions.assertNotNull(operationController);
        Assertions.assertNotNull(operationController.getOperationModel());
        Assertions.assertNotNull(operationController.getErrorView());
    }

    @Test
    public void instance() {
        operationController = OperationController.getInstance();
        Assertions.assertNotNull(operationController);
        Assertions.assertNotNull(OperationController.instance);
        Assertions.assertNotNull(operationController.getOperationModel());
        Assertions.assertNotNull(operationController.getErrorView());
    }

    @Test
    public void checkSingleton() {
        OperationController operationController1 = OperationController.getInstance();
        OperationController operationController2 = OperationController.getInstance();
        Assertions.assertEquals(operationController1, operationController2);
    }

    @Test
    public void testAddOperationToPipeline() {
        OperationModel mockOperationModel = Mockito.mock(OperationModel.class);
        Component componentMock = Mockito.mock(Component.class);
        String operationKey = "";
        Mockito.when(componentMock.getId()).thenReturn(operationKey);
        try (MockedStatic<OperationModel> operationModelStaticMock = Mockito.mockStatic(OperationModel.class)) {
            operationModelStaticMock.when(OperationModel::getInstance).thenReturn(mockOperationModel);
            operationController = OperationController.getInstance();
            operationController.addOperationToPipeline(componentMock);
            verify(mockOperationModel, times(1)).addOperationToPipeline(operationKey);
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddOperationToPipelineWithException() {
        OperationModel mockOperationModel = Mockito.mock(OperationModel.class);
        Component componentMock = Mockito.mock(Component.class);
        ErrorViewPopUp errorViewMock = Mockito.mock(ErrorViewPopUp.class);
        String operationKey = "";
        Mockito.when(componentMock.getId()).thenReturn(operationKey);
        try (MockedStatic<OperationModel> operationModelStaticMock = Mockito.mockStatic(OperationModel.class);
             MockedStatic<ErrorViewPopUp> errorViewPopUpStaticMock = Mockito.mockStatic(ErrorViewPopUp.class)) {
            operationModelStaticMock.when(OperationModel::getInstance).thenReturn(mockOperationModel);
            errorViewPopUpStaticMock.when(ErrorViewPopUp::getInstance).thenReturn(errorViewMock);
            operationController = OperationController.getInstance();
            Mockito.doThrow(new OperationNotSupportedException("Operation not supported")).when(mockOperationModel).addOperationToPipeline(operationKey);

            operationController.addOperationToPipeline(componentMock);
            verify(errorViewMock, times(1)).showPopUpError("OperationNotSupportedException", "Operation not supported");
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}