package ch.supsi.imageEditor.backend.dataaccess.operation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OperationDataAccessTest {
    private OperationDataAccess operationDataAccess;

    @BeforeEach
    public void beforeEach() {
        OperationDataAccess.instance = null;
    }

    @Test
    public void constructor() {
        OperationDataAccess operationDataAccess = new OperationDataAccess();
        assertNotNull(operationDataAccess);
    }

    @Test
    public void instance() {
        OperationDataAccess operationDataAccess = OperationDataAccess.getInstance();
        assertNotNull(operationDataAccess);
        assertNotNull(OperationDataAccess.instance);
    }

    @Test
    public void checkSingleton() {
        OperationDataAccess operationDataAccess1 = OperationDataAccess.getInstance();
        OperationDataAccess operationDataAccess2 = OperationDataAccess.getInstance();
        assertEquals(operationDataAccess1, operationDataAccess2);
    }

    @Test
    void testGetOperationProperties() {
        this.operationDataAccess = OperationDataAccess.getInstance();
        Properties operationProperties = operationDataAccess.getOperationProperties();
        Assertions.assertNotNull(operationProperties, "Le proprietà delle operazioni non devono essere null");
        //Assertions.assertEquals("ConcreteOperation", operationProperties.getProperty("rotate-90-left"),
        // "Il valore dell'operazione 'operation-name' non è quello previsto");
    }
}