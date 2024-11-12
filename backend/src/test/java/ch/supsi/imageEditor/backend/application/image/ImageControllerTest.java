package ch.supsi.imageEditor.backend.application.image;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.application.observer.NotificationServiceInterface;
import ch.supsi.imageEditor.backend.business.images.ImageFactoryInterface;
import ch.supsi.imageEditor.backend.business.pipeline.PipelineModelInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ImageControllerTest {

    @Mock
    ImageFactoryInterface imageReaderFactoryMock;

    @Mock
    PipelineModelInterface pipelineModelMock;

    @Mock
    NotificationServiceInterface notificationServiceMock;

    @InjectMocks
    ImageController imageController;

    @BeforeEach
    public void beforeEach() {
        ImageController.instance = null;

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void constructor() {
        ImageController imageController = new ImageController();
        Assertions.assertNotNull(imageController);
        Assertions.assertNotNull(imageController.getImageReaderFactory());
        Assertions.assertNotNull(imageController.getNotificationService());
        Assertions.assertNotNull(imageController.getPipelineModel());
    }

    @Test
    public void instance() {
        ImageController imageController = ImageController.getInstance();
        Assertions.assertNotNull(imageController);
        Assertions.assertNotNull(ImageController.instance);
        Assertions.assertNotNull(imageController.getImageReaderFactory());
        Assertions.assertNotNull(imageController.getNotificationService());
        Assertions.assertNotNull(imageController.getPipelineModel());
    }

    @Test
    public void checkSingleton() {
        ImageController imageController1 = ImageController.getInstance();
        ImageController imageController2 = ImageController.getInstance();
        Assertions.assertEquals(imageController1, imageController2);
    }

    @Test
    public void readImageTest() {
        InOrder inOrder = inOrder(this.imageReaderFactoryMock, this.notificationServiceMock, this.pipelineModelMock);
        try {
            when(this.pipelineModelMock.getPipeline()).thenReturn(List.of());
            this.imageController.readImage(anyString());
            inOrder.verify(this.imageReaderFactoryMock).readImage(anyString());
            inOrder.verify(this.notificationServiceMock).notify(EventType.OPEN_IMAGE);
            verify(this.pipelineModelMock).getPipeline();
            verify(this.pipelineModelMock, never()).cleanPipeline();
            verify(this.notificationServiceMock, never()).notify(EventType.CLEAR_PIPELINE);

            when(this.pipelineModelMock.getPipeline()).thenReturn(List.of(""));
            this.imageController.readImage(anyString());
            inOrder.verify(this.imageReaderFactoryMock).readImage(anyString());
            inOrder.verify(this.notificationServiceMock).notify(EventType.OPEN_IMAGE);
            inOrder.verify(this.pipelineModelMock).getPipeline();
            inOrder.verify(this.pipelineModelMock).cleanPipeline();
            inOrder.verify(this.notificationServiceMock).notify(EventType.CLEAR_PIPELINE);
        } catch (Exception ignored) {
            ;
        }
    }

    @Test
    public void writeImageTest() {
        InOrder inOrder = inOrder(this.imageReaderFactoryMock, this.notificationServiceMock);
        this.imageController.writeImage(null);
        inOrder.verify(this.imageReaderFactoryMock).writeImage(null, null);
        inOrder.verify(this.notificationServiceMock).notify(EventType.SAVE_IMAGE);

    }

    @Test
    public void getImageTest() {
        this.imageController.getImage();
        verify(this.imageReaderFactoryMock).getImage();
    }

    @Test
    public void getSupportedFormatTest() {
        this.imageController.getSupportedFormat();
        verify(this.imageReaderFactoryMock).getSupportedFormat();
    }

    @Test
    public void getRecentFilesTest() {
        this.imageController.getRecentFiles();
        verify(this.imageReaderFactoryMock).getRecentFiles();
    }

    @Test
    public void closeImageTest() {
        InOrder inOrder = inOrder(this.imageReaderFactoryMock, this.notificationServiceMock, this.pipelineModelMock);
        when(this.pipelineModelMock.getPipeline()).thenReturn(List.of());
        this.imageController.closeImage();
        verify(this.pipelineModelMock).getPipeline();
        verify(this.pipelineModelMock, never()).cleanPipeline();
        verify(this.notificationServiceMock, never()).notify(EventType.CLEAR_PIPELINE);
        inOrder.verify(imageReaderFactoryMock).closeImage();
        inOrder.verify(this.notificationServiceMock).notify(EventType.CLOSE_IMAGE);

        when(this.pipelineModelMock.getPipeline()).thenReturn(List.of(""));
        this.imageController.closeImage();
        inOrder.verify(this.pipelineModelMock).getPipeline();
        inOrder.verify(this.pipelineModelMock).cleanPipeline();
        inOrder.verify(this.notificationServiceMock).notify(EventType.CLEAR_PIPELINE);
        inOrder.verify(imageReaderFactoryMock).closeImage();
        inOrder.verify(this.notificationServiceMock).notify(EventType.CLOSE_IMAGE);

    }

}