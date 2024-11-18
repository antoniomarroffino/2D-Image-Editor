package ch.supsi.imageEditor.backend.application.pipeline;


import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.application.observer.NotificationService;
import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import ch.supsi.imageEditor.backend.business.operation.OperationModel;
import ch.supsi.imageEditor.backend.business.pipeline.PipelineModel;
import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

class PipelineControllerTest {
    @BeforeEach
    public void beforeEach() {
        PipelineController.instance = null;
    }

    @Test
    public void constructor() {
        PipelineController pipelineController = new PipelineController();
        Assertions.assertNotNull(pipelineController);
        Assertions.assertNotNull(pipelineController.getImageModel());
        Assertions.assertNotNull(pipelineController.getPipelineModel());
        Assertions.assertNotNull(pipelineController.getOperationModel());
        Assertions.assertNotNull(pipelineController.getNotificationService());
    }

    @Test
    public void instance() {
        PipelineController pipelineController = PipelineController.getInstance();
        Assertions.assertNotNull(pipelineController);
        Assertions.assertNotNull(PipelineController.instance);
        Assertions.assertNotNull(pipelineController.getImageModel());
        Assertions.assertNotNull(pipelineController.getPipelineModel());
        Assertions.assertNotNull(pipelineController.getOperationModel());
        Assertions.assertNotNull(pipelineController.getNotificationService());
    }

    @Test
    public void checkSingleton() {
        PipelineController pipelineController1 = PipelineController.getInstance();
        PipelineController pipelineController2 = PipelineController.getInstance();
        Assertions.assertEquals(pipelineController1, pipelineController2);
    }

    @Test
    void testAddOperationToPipeline() throws OperationNotSupportedException {
        String operationName = "resize";

        // Mock static methods for PipelineModel, OperationModel, and NotificationService
        try (MockedStatic<PipelineModel> pipelineModelMocked = mockStatic(PipelineModel.class);
             MockedStatic<OperationModel> operationModelMocked = mockStatic(OperationModel.class);
             MockedStatic<NotificationService> notificationServiceMocked = mockStatic(NotificationService.class)) {

            // Mock the static getInstance methods to return mocked instances
            PipelineModel mockPipelineModel = mock(PipelineModel.class);
            OperationModel mockOperationModel = mock(OperationModel.class);
            NotificationService mockNotificationService = mock(NotificationService.class);

            // Define behavior for the mocked static methods
            pipelineModelMocked.when(PipelineModel::getInstance).thenReturn(mockPipelineModel);
            operationModelMocked.when(OperationModel::getInstance).thenReturn(mockOperationModel);
            notificationServiceMocked.when(NotificationService::getInstance).thenReturn(mockNotificationService);

            // Mock behavior of the operation model
            doNothing().when(mockOperationModel).checkOperationExists(operationName);
            doNothing().when(mockPipelineModel).addOperationToPipeline(operationName);

            // Act: Call the method under test
            PipelineController pipelineController = PipelineController.getInstance();
            pipelineController.addOperationToPipeline(operationName);

            // Assert: Verify that the methods were called
            verify(mockOperationModel).checkOperationExists(operationName);
            verify(mockPipelineModel).addOperationToPipeline(operationName);
            verify(mockNotificationService).notify(EventType.ADDED_OPERATION);
        }
    }

    @Test
    void testGetLastOperationAdded() {
        try (MockedStatic<PipelineModel> pipelineModelMocked = mockStatic(PipelineModel.class)) {
            PipelineModel mockPipelineModel = mock(PipelineModel.class);
            String expectedLastOperation = "resize";
            when(mockPipelineModel.getLastOperationAdded()).thenReturn(expectedLastOperation);
            pipelineModelMocked.when(PipelineModel::getInstance).thenReturn(mockPipelineModel);
            PipelineController pipelineController = PipelineController.getInstance();
            String lastOperation = pipelineController.getLastOperationAdded();
            verify(mockPipelineModel).getLastOperationAdded();
            assert lastOperation.equals(expectedLastOperation) : "Expected " + expectedLastOperation + " but got " + lastOperation;
        }
    }

    @Test
    void testDeletePipeline() {
        try (MockedStatic<PipelineModel> pipelineModelMocked = mockStatic(PipelineModel.class);
             MockedStatic<NotificationService> notificationServiceMocked = mockStatic(NotificationService.class)) {
            PipelineModel mockPipelineModel = mock(PipelineModel.class);
            NotificationService mockNotificationService = mock(NotificationService.class);
            pipelineModelMocked.when(PipelineModel::getInstance).thenReturn(mockPipelineModel);
            notificationServiceMocked.when(NotificationService::getInstance).thenReturn(mockNotificationService);
            PipelineController pipelineController = PipelineController.getInstance();
            pipelineController.deletePipeline();
            verify(mockPipelineModel).cleanPipeline();
            verify(mockNotificationService).notify(EventType.CLEAR_PIPELINE);
        }
    }

    @Test
    void testRunPipeline() {
        try (MockedStatic<PipelineModel> pipelineModelMocked = mockStatic(PipelineModel.class);
             MockedStatic<OperationModel> operationModelMocked = mockStatic(OperationModel.class);
             MockedStatic<ImageFactory> imageFactoryMocked = mockStatic(ImageFactory.class);
             MockedStatic<NotificationService> notificationServiceMocked = mockStatic(NotificationService.class)) {

            PipelineModel mockPipelineModel = mock(PipelineModel.class);
            OperationModel mockOperationModel = mock(OperationModel.class);
            ImageFactory mockImageFactory = mock(ImageFactory.class);
            NotificationService mockNotificationService = mock(NotificationService.class);

            pipelineModelMocked.when(PipelineModel::getInstance).thenReturn(mockPipelineModel);
            operationModelMocked.when(OperationModel::getInstance).thenReturn(mockOperationModel);
            imageFactoryMocked.when(ImageFactory::getInstance).thenReturn(mockImageFactory);
            notificationServiceMocked.when(NotificationService::getInstance).thenReturn(mockNotificationService);

            AbstractImage mockImage = mock(AbstractImage.class);
            when(mockImageFactory.getImage()).thenReturn(mockImage);

            pipelineModelMocked.when(mockPipelineModel::getPipeline).thenReturn(null);
            when(mockOperationModel.executeOperations(any(), any())).thenReturn(mockImage);

            PipelineController pipelineController = PipelineController.getInstance();
            pipelineController.runPipeline();

            verify(mockOperationModel).executeOperations(any(), any());
            verify(mockImageFactory).setImage(mockImage);
            verify(mockNotificationService, times(2)).notify(any(EventType.class));
            verify(mockPipelineModel).cleanPipeline();
        }
    }


}