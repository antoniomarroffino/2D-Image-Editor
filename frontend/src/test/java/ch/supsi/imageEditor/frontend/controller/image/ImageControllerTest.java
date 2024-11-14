package ch.supsi.imageEditor.frontend.controller.image;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.model.image.ImageModel;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ImageControllerTest {
    private final DataView dataViewMock1 = Mockito.mock(DataView.class);
    private final DataView dataViewMock2 = Mockito.mock(DataView.class);

    private ImageController imageController;

    @BeforeEach
    public void beforeEach() {
        ImageController.instance = null;
    }

    @Test
    public void constructor() {
        imageController = new ImageController();
        assertNotNull(imageController);
        assertNotNull(imageController.getPubSubModel());
        assertNotNull(imageController.getImageModel());
        assertNotNull(imageController.getOnEventDoActionMap());
    }

    @Test
    public void instance() {
        imageController = ImageController.getInstance();
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
    void testOpenImage() {
        ImageModel mockImageModel = Mockito.mock(ImageModel.class);
        try (MockedStatic<ImageModel> imageModelStaticMock = Mockito.mockStatic(ImageModel.class)) {
            imageModelStaticMock.when(ImageModel::getInstance).thenReturn(mockImageModel);
            imageController = ImageController.getInstance();

            imageController.initialize(Arrays.asList(dataViewMock1, dataViewMock2));

            imageController.update(EventType.OPEN_IMAGE);
            verify(mockImageModel, times(1)).loadCurrentImage();
            verify(dataViewMock1, times(1)).update(EventType.OPEN_IMAGE);
            verify(dataViewMock2, times(1)).update(EventType.OPEN_IMAGE);
        }
    }

    @Test
    void testCloseImage() {
        ImageModel mockImageModel = Mockito.mock(ImageModel.class);
        try (MockedStatic<ImageModel> imageModelStaticMock = Mockito.mockStatic(ImageModel.class)) {
            imageModelStaticMock.when(ImageModel::getInstance).thenReturn(mockImageModel);
            imageController = ImageController.getInstance();

            imageController.initialize(Arrays.asList(dataViewMock1, dataViewMock2));

            imageController.update(EventType.CLOSE_IMAGE);
            verify(mockImageModel, times(1)).closeCurrentImage();
            verify(dataViewMock1, times(1)).update(EventType.CLOSE_IMAGE);
            verify(dataViewMock2, times(1)).update(EventType.CLOSE_IMAGE);
        }
    }
}