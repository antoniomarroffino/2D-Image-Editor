package ch.supsi.imageEditor.backend.application.operation;

import ch.supsi.imageEditor.backend.business.operation.AvailableOperationModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Set;

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
    }

    @Test
    public void instance() {
        OperationController operationController = OperationController.getInstance();
        Assertions.assertNotNull(operationController);
        Assertions.assertNotNull(OperationController.instance);
    }

    @Test
    public void checkSingleton() {
        OperationController operationController1 = OperationController.getInstance();
        OperationController operationController2 = OperationController.getInstance();
        Assertions.assertEquals(operationController1, operationController2);
    }

    @Test
    public void getOperationsTagTest() {
        AvailableOperationModel mockAvailableOperationModel = Mockito.mock(AvailableOperationModel.class);
        Set<String> mockOperationsTag = Set.of("rotate-90-left", "rotate-90-right");
        when(mockAvailableOperationModel.getOperationsTag()).thenReturn(mockOperationsTag);
        try (MockedStatic<AvailableOperationModel> mockedStatic = Mockito.mockStatic(AvailableOperationModel.class)) {
            mockedStatic.when(AvailableOperationModel::getInstance).thenReturn(mockAvailableOperationModel);
            this.operationController = OperationController.getInstance();
            Set<String> operationsTag = this.operationController.getOperationsTag();
            Assertions.assertEquals(mockOperationsTag, operationsTag);
        }
    }
}