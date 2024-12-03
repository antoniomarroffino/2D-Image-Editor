package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.operation.allOperations.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OperationModelTest {
    private OperationModel operationModel;

    @BeforeEach
    public void beforeEach() {
        OperationModel.instance = null;
    }

    @Test
    public void constructor() {
        OperationModel operationModel = new OperationModel();
        Assertions.assertNotNull(operationModel);
    }

    @Test
    public void instance() {
        OperationModel operationModel = OperationModel.getInstance();
        Assertions.assertNotNull(operationModel);
        Assertions.assertNotNull(OperationModel.instance);
    }

    @Test
    public void checkSingleton() {
        OperationModel operationModel1 = OperationModel.getInstance();
        OperationModel operationModel2 = OperationModel.getInstance();
        assertEquals(operationModel1, operationModel2);
    }

    @Test
    public void executeOperationsTest() {
        AbstractImage mockImage = mock(AbstractImage.class);
        List<String> operationsTag = List.of("rotate-90-left", "rotate-90-right");
        Operation mockOperation = mock(Operation.class);

        Map<String, Operation> mockOperationsMap = new HashMap<>();
        mockOperationsMap.put("rotate-90-left", mockOperation);
        mockOperationsMap.put("rotate-90-right", mockOperation);

        this.operationModel = OperationModel.getInstance();

        when(mockOperation.doOperation(any(AbstractImage.class))).thenReturn(mockImage);
        AbstractImage resultImage = this.operationModel.executeOperations(operationsTag, mockImage, mockOperationsMap);

        verify(mockOperation, times(2)).doOperation(mockImage);

        assertEquals(mockImage, resultImage);
    }


}