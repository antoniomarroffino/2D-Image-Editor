package ch.supsi.imageEditor.backend.application.operation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class OperationControllerTest {
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
}