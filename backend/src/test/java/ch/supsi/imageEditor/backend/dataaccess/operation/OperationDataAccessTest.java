package ch.supsi.imageEditor.backend.dataaccess.operation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperationDataAccessTest {
    @BeforeEach
    public void beforeEach() {
        OperationDataAccess.instance = null;
    }

    @Test
    public void constructor() {
        OperationDataAccess operationDataAccess = new OperationDataAccess();
        Assertions.assertNotNull(operationDataAccess);
    }

    @Test
    public void instance() {
        OperationDataAccess operationDataAccess = OperationDataAccess.getInstance();
        Assertions.assertNotNull(operationDataAccess);
        Assertions.assertNotNull(OperationDataAccess.instance);
    }

    @Test
    public void checkSingleton() {
        OperationDataAccess operationDataAccess1 = OperationDataAccess.getInstance();
        OperationDataAccess operationDataAccess2 = OperationDataAccess.getInstance();
        Assertions.assertEquals(operationDataAccess1, operationDataAccess2);
    }
}