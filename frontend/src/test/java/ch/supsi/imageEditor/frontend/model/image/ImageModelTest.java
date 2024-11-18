package ch.supsi.imageEditor.frontend.model.image;

import ch.supsi.imageEditor.backend.application.image.ImageController;
import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class ImageModelTest {
    private ImageModel imageModel;
    private AbstractImage mockImage;

    @BeforeEach
    public void beforeEach() {
        ImageModel.instance = null;
        this.mockImage = Mockito.mock(AbstractImage.class);
    }

    @Test
    public void constructor() {
        this.imageModel = new ImageModel();
        Assertions.assertNotNull(this.imageModel);
    }

    @Test
    public void instance() {
        this.imageModel = ImageModel.getInstance();
        Assertions.assertNotNull(this.imageModel);
        Assertions.assertNotNull(ImageModel.instance);
    }

    @Test
    public void checkSingleton() {
        ImageModel imageModel1 = ImageModel.getInstance();
        ImageModel imageModel2 = ImageModel.getInstance();
        Assertions.assertEquals(imageModel1, imageModel2);
    }

    @Test
    void testLoadCurrentImage() {
        ImageController mockBackendImageController = Mockito.mock(ImageController.class);
        try (MockedStatic<ImageController> backendImageControllerStaticMock = Mockito.mockStatic(ImageController.class)) {
            backendImageControllerStaticMock.when(ImageController::getInstance).thenReturn(mockBackendImageController);
            this.imageModel = ImageModel.getInstance();
            when(mockBackendImageController.getImage()).thenReturn(this.mockImage);
            imageModel.loadCurrentImage();
            verify(mockBackendImageController, times(1)).getImage();
            Assertions.assertSame(this.mockImage, imageModel.getImage());
        }
    }

    @Test
    void testCloseCurrentImage() {
        ImageController mockBackendImageController = Mockito.mock(ImageController.class);
        try (MockedStatic<ImageController> backendImageControllerStaticMock = Mockito.mockStatic(ImageController.class)) {
            backendImageControllerStaticMock.when(ImageController::getInstance).thenReturn(mockBackendImageController);
            this.imageModel = ImageModel.getInstance();
            when(mockBackendImageController.getImage()).thenReturn(this.mockImage);
            imageModel.loadCurrentImage();
            imageModel.closeCurrentImage();
            Assertions.assertNull(imageModel.getImage(), "Current image should be null after closing.");
        }
    }

    @Test
    void testGetWidthImage() {
        ImageController mockBackendImageController = Mockito.mock(ImageController.class);
        try (MockedStatic<ImageController> backendImageControllerStaticMock = Mockito.mockStatic(ImageController.class)) {
            backendImageControllerStaticMock.when(ImageController::getInstance).thenReturn(mockBackendImageController);
            this.imageModel = ImageModel.getInstance();
            when(mockImage.getWidth()).thenReturn(1920);
            when(mockBackendImageController.getImage()).thenReturn(this.mockImage);
            imageModel.loadCurrentImage();
            Assertions.assertEquals(1920, imageModel.getWidthImage());
        }
    }

    @Test
    void testGetHeightImage() {
        ImageController mockBackendImageController = Mockito.mock(ImageController.class);
        try (MockedStatic<ImageController> backendImageControllerStaticMock = Mockito.mockStatic(ImageController.class)) {
            backendImageControllerStaticMock.when(ImageController::getInstance).thenReturn(mockBackendImageController);
            this.imageModel = ImageModel.getInstance();
            when(mockImage.getHeight()).thenReturn(1080);
            when(mockBackendImageController.getImage()).thenReturn(this.mockImage);
            imageModel.loadCurrentImage();
            Assertions.assertEquals(1080, imageModel.getHeightImage());
        }
    }

    @Test
    void testGetPixelColors() {
        ImageController mockBackendImageController = Mockito.mock(ImageController.class);
        try (MockedStatic<ImageController> backendImageControllerStaticMock = Mockito.mockStatic(ImageController.class)) {
            backendImageControllerStaticMock.when(ImageController::getInstance).thenReturn(mockBackendImageController);
            this.imageModel = ImageModel.getInstance();
            Pixel mockPixel = mock(Pixel.class);
            when(mockPixel.getRed()).thenReturn(255);
            when(mockPixel.getGreen()).thenReturn(128);
            when(mockPixel.getBlue()).thenReturn(64);
            when(this.mockImage.getPixel(10, 20)).thenReturn(mockPixel);
            when(mockBackendImageController.getImage()).thenReturn(this.mockImage);
            imageModel.loadCurrentImage();
            Assertions.assertEquals(255, imageModel.getPixelRed(10, 20));
            Assertions.assertEquals(128, imageModel.getPixelGreen(10, 20));
            Assertions.assertEquals(64, imageModel.getPixelBlue(10, 20));
        }
    }

    @Test
    void testDefaultValues() {
        ImageController mockBackendImageController = Mockito.mock(ImageController.class);
        try (MockedStatic<ImageController> backendImageControllerStaticMock = Mockito.mockStatic(ImageController.class)) {
            backendImageControllerStaticMock.when(ImageController::getInstance).thenReturn(mockBackendImageController);
            this.imageModel = ImageModel.getInstance();
            Assertions.assertEquals(0, imageModel.getWidthImage());
            Assertions.assertEquals(0, imageModel.getHeightImage());
            Assertions.assertThrows(NullPointerException.class, () -> imageModel.getPixelRed(0, 0));
        }
    }
}