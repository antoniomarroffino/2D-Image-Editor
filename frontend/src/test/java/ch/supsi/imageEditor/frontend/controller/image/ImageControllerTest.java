package ch.supsi.imageEditor.frontend.controller.image;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.model.image.ImageModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class ImageControllerTest {
    @Mock
    private ImageModelInterface imageModelMock;

    @Mock
    private DataView dataViewMock1, dataViewMock2;

    @InjectMocks
    private ImageController imageController;

    @BeforeEach
    public void beforeEach() {
        ImageController.instance = null;
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void constructor() {
        ImageController imageController = new ImageController();
        assertNotNull(imageController);
        assertNotNull(imageController.getPubSubModel());
        assertNotNull(imageController.getImageModel());
        assertNotNull(imageController.getOnEventDoActionMap());
    }

    @Test
    public void instance() {
        ImageController imageController = ImageController.getInstance();
        assertNotNull(imageController);
        assertNotNull(ImageController.instance);
        assertNotNull(imageController.getPubSubModel());
        assertNotNull(imageController.getImageModel());
        assertNotNull(imageController.getOnEventDoActionMap());
    }

    @Test
    public void checkSingleton() {
        ImageController imageController1 = ImageController.getInstance();
        ImageController imageController2 = ImageController.getInstance();
        Assertions.assertEquals(imageController1, imageController2);
    }

    @Test
    void testInitialize() {
        List<DataView> views = Arrays.asList(dataViewMock1, dataViewMock2);
        imageController.initialize(views);
        imageController.update(EventType.OPEN_IMAGE);
        verify(imageModelMock, times(1)).loadCurrentImage();
    }

    @Test
    void testOpenImage() {
        List<DataView> views = Arrays.asList(dataViewMock1, dataViewMock2);
        imageController.initialize(views);
        imageController.update(EventType.OPEN_IMAGE);
        verify(imageModelMock, times(1)).loadCurrentImage();
        verify(dataViewMock1, times(1)).update(EventType.OPEN_IMAGE);
        verify(dataViewMock2, times(1)).update(EventType.OPEN_IMAGE);
    }

    @Test
    void testCloseImage() {
        List<DataView> views = Arrays.asList(dataViewMock1, dataViewMock2);
        imageController.initialize(views);
        imageController.update(EventType.CLOSE_IMAGE);
        verify(imageModelMock, times(1)).closeCurrentImage();
        verify(dataViewMock1, times(1)).update(EventType.CLOSE_IMAGE);
        verify(dataViewMock2, times(1)).update(EventType.CLOSE_IMAGE);
    }
}