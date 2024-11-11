package ch.supsi.imageEditor.backend.business.operation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperationModelTest {

    @BeforeEach
    public void beforeEach() {
        OperationModel.instance = null;
    }

    @Test
    public void constructor() {
        OperationModel operationModel = new OperationModel();
        Assertions.assertNotNull(operationModel);
        Assertions.assertNotNull(operationModel.getOperationDataAccess());
        Assertions.assertNotNull(operationModel.getSupportedOperations());
        Assertions.assertNotNull(operationModel.getOperationsMap());
        Assertions.assertNotNull(operationModel.getOperationProperties());
    }

    @Test
    public void instance() {
        OperationModel operationModel = OperationModel.getInstance();
        Assertions.assertNotNull(operationModel);
        Assertions.assertNotNull(OperationModel.instance);
        Assertions.assertNotNull(operationModel.getOperationDataAccess());
        Assertions.assertNotNull(operationModel.getSupportedOperations());
        Assertions.assertNotNull(operationModel.getOperationsMap());
        Assertions.assertNotNull(operationModel.getOperationProperties());
    }

    @Test
    public void checkSingleton() {
        OperationModel operationModel1 = OperationModel.getInstance();
        OperationModel operationModel2 = OperationModel.getInstance();
        Assertions.assertEquals(operationModel1, operationModel2);
    }
}