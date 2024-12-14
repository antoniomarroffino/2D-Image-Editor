package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.dataaccess.operation.OperationDataAccess;
import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AvailableOperationModelTest {
    private AvailableOperationModel availableOperationModel;

    @BeforeEach
    public void beforeEach() {
        AvailableOperationModel.instance = null;
    }

    @Test
    public void constructor() {
        AvailableOperationModel availableOperationModel = new AvailableOperationModel();
        Assertions.assertNotNull(availableOperationModel);
    }

    @Test
    public void instance() {
        AvailableOperationModel availableOperationModel = AvailableOperationModel.getInstance();
        Assertions.assertNotNull(availableOperationModel);
        Assertions.assertNotNull(AvailableOperationModel.instance);
    }

    @Test
    public void checkSingleton() {
        AvailableOperationModel availableOperationModel1 = AvailableOperationModel.getInstance();
        AvailableOperationModel availableOperationModel2 = AvailableOperationModel.getInstance();
        assertEquals(availableOperationModel1, availableOperationModel2);
    }

    @Test
    public void loadOperationsMapTest() {
        OperationDataAccess mockOperationDataAccess = mock(OperationDataAccess.class);
        Properties mockProperties = new Properties();
        mockProperties.setProperty("rotate-90-left", "ch.supsi.imageEditor.backend.business.operation.allOperations.Rotate90DegreesLeft");
        when(mockOperationDataAccess.getOperationProperties()).thenReturn(mockProperties);
        when(mockOperationDataAccess.getOperationsTag()).thenReturn(Set.of("rotate-90-left", "crop"));
        try (MockedStatic<OperationDataAccess> mockedStatic = mockStatic(OperationDataAccess.class)) {
            mockedStatic.when(OperationDataAccess::getInstance).thenReturn(mockOperationDataAccess);
            this.availableOperationModel = AvailableOperationModel.getInstance();
            verify(mockOperationDataAccess, times(1)).getOperationProperties();
        }
    }

    @Test
    public void loadOperationsMapTest2() {
        OperationDataAccess mockOperationDataAccess = mock(OperationDataAccess.class);
        Properties mockProperties = new Properties();
        mockProperties.setProperty("crop", "non.existing.ClassName");
        when(mockOperationDataAccess.getOperationProperties()).thenReturn(mockProperties);
        when(mockOperationDataAccess.getOperationsTag()).thenReturn(Set.of("rotate-90-left", "crop"));
        try (MockedStatic<OperationDataAccess> mockedStatic = mockStatic(OperationDataAccess.class)) {
            mockedStatic.when(OperationDataAccess::getInstance).thenReturn(mockOperationDataAccess);
            this.availableOperationModel = AvailableOperationModel.getInstance();
            verify(mockOperationDataAccess, times(1)).getOperationProperties();
        }
    }

    @Test
    public void checkOperationExistsTest() {
        OperationDataAccess mockOperationDataAccess = mock(OperationDataAccess.class);
        Set<String> supportedOperations = Set.of("rotate-90-left", "rotate-90-right");
        when(mockOperationDataAccess.getOperationsTag()).thenReturn(supportedOperations);
        when(mockOperationDataAccess.getOperationProperties()).thenReturn(new Properties());
        try (MockedStatic<OperationDataAccess> mockedStatic = mockStatic(OperationDataAccess.class)) {
            mockedStatic.when(OperationDataAccess::getInstance).thenReturn(mockOperationDataAccess);
            this.availableOperationModel = AvailableOperationModel.getInstance();
            assertDoesNotThrow(() -> this.availableOperationModel.checkOperationExists("rotate-90-left"));
            OperationNotSupportedException exception = assertThrows(
                    OperationNotSupportedException.class,
                    () -> this.availableOperationModel.checkOperationExists("resize")
            );
            assertEquals("Operation resize is not supported", exception.getMessage());
        }
    }

    @Test
    public void getOperationsTagTest() {
        this.availableOperationModel = AvailableOperationModel.getInstance();
        Set<String> supportedOperationsExpected = Set.of("rotate-90-right", "rotate-90-left", "flip-side-to-side", "flip-upside-down", "negative");
        Set<String> supportedOperations = this.availableOperationModel.getOperationsTag();
        Assertions.assertEquals(supportedOperationsExpected, supportedOperations);
    }
}
