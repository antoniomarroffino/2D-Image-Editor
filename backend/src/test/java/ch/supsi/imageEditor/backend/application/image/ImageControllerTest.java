package ch.supsi.imageEditor.backend.application.image;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.application.observer.NotificationService;
import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import ch.supsi.imageEditor.backend.business.pipeline.PipelineModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;
import java.util.List;

import static org.mockito.Mockito.*;


class ImageControllerTest {

    private ImageController imageController;

    @BeforeEach
    public void beforeEach() {
        ImageController.instance = null;
    }

    @Test
    public void constructor() {
        ImageController imageController = new ImageController();
        Assertions.assertNotNull(imageController);
    }

    @Test
    public void instance() {
        ImageController imageController = ImageController.getInstance();
        Assertions.assertNotNull(imageController);
        Assertions.assertNotNull(ImageController.instance);
    }

    @Test
    public void checkSingleton() {
        ImageController imageController1 = ImageController.getInstance();
        ImageController imageController2 = ImageController.getInstance();
        Assertions.assertEquals(imageController1, imageController2);
    }

    @Test
    public void readImageTest() {
        ImageFactory mockImageFactory = Mockito.mock(ImageFactory.class);
        PipelineModel mockPipelineModel = Mockito.mock(PipelineModel.class);
        NotificationService mockNotificationService = Mockito.mock(NotificationService.class);

        try (MockedStatic<ImageFactory> imageFactoryStaticMock = Mockito.mockStatic(ImageFactory.class);
             MockedStatic<PipelineModel> pipelineModelStaticMock = Mockito.mockStatic(PipelineModel.class);
             MockedStatic<NotificationService> notificationServiceStaticMock = Mockito.mockStatic(NotificationService.class)) {

            imageFactoryStaticMock.when(ImageFactory::getInstance).thenReturn(mockImageFactory);
            pipelineModelStaticMock.when(PipelineModel::getInstance).thenReturn(mockPipelineModel);
            notificationServiceStaticMock.when(NotificationService::getInstance).thenReturn(mockNotificationService);
            this.imageController = ImageController.getInstance();

            InOrder inOrder = inOrder(mockImageFactory, mockNotificationService, mockPipelineModel);
            try {
                when(mockPipelineModel.getPipeline()).thenReturn(List.of());
                this.imageController.readImage(anyString());
                inOrder.verify(mockImageFactory).readImage(anyString());
                inOrder.verify(mockNotificationService).notify(EventType.OPEN_IMAGE);
                verify(mockPipelineModel).getPipeline();
                verify(mockPipelineModel, never()).cleanPipeline();
                verify(mockNotificationService, never()).notify(EventType.CLEAR_PIPELINE);

                when(mockPipelineModel.getPipeline()).thenReturn(List.of(""));
                this.imageController.readImage(anyString());
                inOrder.verify(mockImageFactory).readImage(anyString());
                inOrder.verify(mockNotificationService).notify(EventType.OPEN_IMAGE);
                inOrder.verify(mockPipelineModel).getPipeline();
                inOrder.verify(mockPipelineModel).cleanPipeline();
                inOrder.verify(mockNotificationService).notify(EventType.CLEAR_PIPELINE);
            } catch (Exception ignored) {
                ;
            }
        } catch (Exception e) {
            Assertions.fail("Eccezione inaspettata: " + e.getMessage());
        }
    }

    @Test
    public void writeImageTest() {
        ImageFactory mockImageFactory = Mockito.mock(ImageFactory.class);
        PipelineModel mockPipelineModel = Mockito.mock(PipelineModel.class);
        NotificationService mockNotificationService = Mockito.mock(NotificationService.class);

        File mockSource = Mockito.mock(File.class);
        File mockDestination = Mockito.mock(File.class);
        try (MockedStatic<ImageFactory> imageFactoryStaticMock = Mockito.mockStatic(ImageFactory.class);
             MockedStatic<PipelineModel> pipelineModelStaticMock = Mockito.mockStatic(PipelineModel.class);
             MockedStatic<NotificationService> notificationServiceStaticMock = Mockito.mockStatic(NotificationService.class)) {

            imageFactoryStaticMock.when(ImageFactory::getInstance).thenReturn(mockImageFactory);
            pipelineModelStaticMock.when(PipelineModel::getInstance).thenReturn(mockPipelineModel);
            notificationServiceStaticMock.when(NotificationService::getInstance).thenReturn(mockNotificationService);

            this.imageController = ImageController.getInstance();

            imageController.writeImage(null);
            imageController.writeImage(mockSource, mockDestination);

            InOrder inOrder = inOrder(mockImageFactory, mockNotificationService);
            inOrder.verify(mockImageFactory).writeImage(null, null);
            inOrder.verify(mockNotificationService).notify(EventType.SAVE_IMAGE);

            InOrder inOrder2 = inOrder(mockImageFactory, mockNotificationService);
            inOrder2.verify(mockImageFactory).writeImage(null, null);
            inOrder2.verify(mockNotificationService).notify(EventType.SAVE_IMAGE);
        } catch (Exception e) {
            Assertions.fail("Eccezione inaspettata: " + e.getMessage());
        }
    }


    @Test
    public void getImageTest() {
        ImageFactory mockImageFactory = Mockito.mock(ImageFactory.class);
        PipelineModel mockPipelineModel = Mockito.mock(PipelineModel.class);
        NotificationService mockNotificationService = Mockito.mock(NotificationService.class);
        try (MockedStatic<ImageFactory> imageFactoryStaticMock = Mockito.mockStatic(ImageFactory.class);
             MockedStatic<PipelineModel> pipelineModelStaticMock = Mockito.mockStatic(PipelineModel.class);
             MockedStatic<NotificationService> notificationServiceStaticMock = Mockito.mockStatic(NotificationService.class)) {

            imageFactoryStaticMock.when(ImageFactory::getInstance).thenReturn(mockImageFactory);
            pipelineModelStaticMock.when(PipelineModel::getInstance).thenReturn(mockPipelineModel);
            notificationServiceStaticMock.when(NotificationService::getInstance).thenReturn(mockNotificationService);

            this.imageController = ImageController.getInstance();

            imageController.getImage();

            verify(mockImageFactory).getImage();
        } catch (Exception e) {
            Assertions.fail("Eccezione inaspettata: " + e.getMessage());
        }
    }

    @Test
    public void getSupportedFormatTest() {
        ImageFactory mockImageFactory = Mockito.mock(ImageFactory.class);
        try (MockedStatic<ImageFactory> imageFactoryStaticMock = Mockito.mockStatic(ImageFactory.class)) {
            imageFactoryStaticMock.when(ImageFactory::getInstance).thenReturn(mockImageFactory);
            this.imageController = ImageController.getInstance();


            this.imageController.getSupportedFormat();
            verify(mockImageFactory).getSupportedFormat();
        } catch (Exception e) {
            Assertions.fail("Eccezione inaspettata: " + e.getMessage());
        }
    }

    @Test
    public void getRecentFilesTest() {
        ImageFactory mockImageFactory = Mockito.mock(ImageFactory.class);
        try (MockedStatic<ImageFactory> imageFactoryStaticMock = Mockito.mockStatic(ImageFactory.class)) {
            imageFactoryStaticMock.when(ImageFactory::getInstance).thenReturn(mockImageFactory);
            this.imageController = ImageController.getInstance();

            this.imageController.getRecentFiles();
            verify(mockImageFactory).getRecentFiles();
        } catch (Exception e) {
            Assertions.fail("Eccezione inaspettata: " + e.getMessage());
        }
    }

    @Test
    public void closeImageTest() {
        ImageFactory mockImageFactory = Mockito.mock(ImageFactory.class);
        PipelineModel mockPipelineModel = Mockito.mock(PipelineModel.class);
        NotificationService mockNotificationService = Mockito.mock(NotificationService.class);

        try (MockedStatic<ImageFactory> imageFactoryStaticMock = Mockito.mockStatic(ImageFactory.class);
             MockedStatic<PipelineModel> pipelineModelStaticMock = Mockito.mockStatic(PipelineModel.class);
             MockedStatic<NotificationService> notificationServiceStaticMock = Mockito.mockStatic(NotificationService.class)) {

            imageFactoryStaticMock.when(ImageFactory::getInstance).thenReturn(mockImageFactory);
            pipelineModelStaticMock.when(PipelineModel::getInstance).thenReturn(mockPipelineModel);
            notificationServiceStaticMock.when(NotificationService::getInstance).thenReturn(mockNotificationService);
            this.imageController = ImageController.getInstance();

            InOrder inOrder = inOrder(mockImageFactory, mockNotificationService, mockPipelineModel);
            when(mockPipelineModel.getPipeline()).thenReturn(List.of());
            this.imageController.closeImage();
            verify(mockPipelineModel).getPipeline();
            verify(mockPipelineModel, never()).cleanPipeline();
            verify(mockNotificationService, never()).notify(EventType.CLEAR_PIPELINE);
            inOrder.verify(mockImageFactory).closeImage();
            inOrder.verify(mockNotificationService).notify(EventType.CLOSE_IMAGE);

            when(mockPipelineModel.getPipeline()).thenReturn(List.of(""));
            this.imageController.closeImage();
            inOrder.verify(mockPipelineModel).getPipeline();
            inOrder.verify(mockPipelineModel).cleanPipeline();
            inOrder.verify(mockNotificationService).notify(EventType.CLEAR_PIPELINE);
            inOrder.verify(mockImageFactory).closeImage();
            inOrder.verify(mockNotificationService).notify(EventType.CLOSE_IMAGE);
        } catch (Exception e) {
            Assertions.fail("Eccezione inaspettata: " + e.getMessage());
        }
    }

}