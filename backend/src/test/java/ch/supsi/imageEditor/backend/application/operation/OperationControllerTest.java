package ch.supsi.imageEditor.backend.application.operation;

import ch.supsi.imageEditor.backend.business.operation.OperationModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class OperationControllerTest {

    private OperationController operationController;

    @BeforeEach
    public void beforeEach() {
        OperationController.instance = null;
    }

    @Test
    public void constructor() {
        OperationController operationController = new OperationController();
        Assertions.assertNotNull(operationController);
        Assertions.assertNotNull(operationController.getOperationModel());
    }

    @Test
    public void instance() {
        OperationController operationController = OperationController.getInstance();
        Assertions.assertNotNull(operationController);
        Assertions.assertNotNull(OperationController.instance);
        Assertions.assertNotNull(operationController.getOperationModel());
    }

    @Test
    public void checkSingleton() {
        OperationController operationController1 = OperationController.getInstance();
        OperationController operationController2 = OperationController.getInstance();
        Assertions.assertEquals(operationController1, operationController2);
    }

    @Test
    public void getOperationsTagTest() {
        OperationModel mockOperationModel = Mockito.mock(OperationModel.class);
        Set<String> mockOperationsTag = Set.of("rotate", "resize", "crop");
        when(mockOperationModel.getOperationsTag()).thenReturn(mockOperationsTag);
        try (MockedStatic<OperationModel> operationModelStaticMock = Mockito.mockStatic(OperationModel.class)) {
            operationModelStaticMock.when(OperationModel::getInstance).thenReturn(mockOperationModel);
            this.operationController = OperationController.getInstance();
            Set<String> operationsTag = this.operationController.getOperationsTag();
            Assertions.assertEquals(mockOperationsTag, operationsTag);
            verify(mockOperationModel).getOperationsTag();
        }
    }
}