package ch.supsi.imageEditor.frontend.model.persist;

import ch.supsi.imageEditor.backend.application.image.ImageController;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

class PersistImageModelTest {
    private PersistImageModel persistImageModel;
    private ImageController mockImageController;

    @BeforeEach
    public void beforeEach() {
        PersistImageModel.instance = null;
        mockImageController = Mockito.mock(ImageController.class);
    }

    @Test
    public void constructor() {
        this.persistImageModel = new PersistImageModel();
        Assertions.assertNotNull(this.persistImageModel);
        Assertions.assertNotNull(this.persistImageModel.getImageController());
        Assertions.assertNull(this.persistImageModel.getCurrentFile());
        Assertions.assertFalse(this.persistImageModel.isAlreadySave());
    }

    @Test
    public void instance() {
        this.persistImageModel = PersistImageModel.getInstance();
        Assertions.assertNotNull(this.persistImageModel);
        Assertions.assertNotNull(PersistImageModel.instance);
        Assertions.assertNotNull(this.persistImageModel.getImageController());
        Assertions.assertNull(this.persistImageModel.getCurrentFile());
        Assertions.assertFalse(this.persistImageModel.isAlreadySave());
    }

    @Test
    public void checkSingleton() {
        PersistImageModel persistImageModel1 = PersistImageModel.getInstance();
        PersistImageModel persistImageModel2 = PersistImageModel.getInstance();
        Assertions.assertEquals(persistImageModel1, persistImageModel2);
    }

    @Test
    void getSupportedFormats() {
        this.persistImageModel = PersistImageModel.getInstance();
        Set<String> supportedFormats = Set.of("PBM", "PGM", "PPM");
        when(mockImageController.getSupportedFormat()).thenReturn(supportedFormats);
        Set<String> result = this.persistImageModel.getSupportedFormats();
        Assertions.assertEquals(supportedFormats, result);
    }

    @Test
    void getRecentFiles() {
        try (MockedStatic<ImageController> imageControllerStaticMock = Mockito.mockStatic(ImageController.class)) {
            imageControllerStaticMock.when(ImageController::getInstance).thenReturn(mockImageController);
            this.persistImageModel = PersistImageModel.getInstance();
            when(mockImageController.getRecentFiles()).thenReturn(List.of("file1.ppm", "file2.pgm", "file3.pbm"));
            Assertions.assertEquals(3, this.persistImageModel.getRecentFiles().size());
            Assertions.assertEquals("file1.ppm", this.persistImageModel.getRecentFiles().get(0));
        }
    }

    @Test
    void setNewSavingFile() {
        this.persistImageModel = PersistImageModel.getInstance();
        File file = new File("testImage.ppm");
        this.persistImageModel.setNewSavingFile(file);
        Assertions.assertEquals(file, this.persistImageModel.getCurrentFile());
    }

    @Test
    void existCurrentFile() {
        this.persistImageModel = PersistImageModel.getInstance();
        File file = new File("testImage.ppm");
        this.persistImageModel.setNewSavingFile(file);
        Assertions.assertTrue(this.persistImageModel.existCurrentFile());
        this.persistImageModel.setNewSavingFile(null);
        Assertions.assertFalse(this.persistImageModel.existCurrentFile());
    }

    @Test
    void isAlreadySave() {
        this.persistImageModel = PersistImageModel.getInstance();
        Assertions.assertFalse(this.persistImageModel.isAlreadySave());
        this.persistImageModel.setAlreadySave(true);
        Assertions.assertTrue(this.persistImageModel.isAlreadySave());
    }

    @Test
    void loadImage_validFormat() throws FormatNotSupportedException, IOException, ImageHeaderUncorrectException {
        File file = new File("validImage.ppm");
        try (MockedStatic<ImageController> imageControllerStaticMock = Mockito.mockStatic(ImageController.class)) {
            imageControllerStaticMock.when(ImageController::getInstance).thenReturn(mockImageController);
            this.persistImageModel = PersistImageModel.getInstance();
            this.persistImageModel.loadImage(file);
            verify(mockImageController, times(1)).readImage(file.getAbsolutePath());
        }
    }

    @Test
    void loadImage_invalidFormat() {
        File file = new File("invalidImage.xyz");
        try (MockedStatic<ImageController> imageControllerStaticMock = Mockito.mockStatic(ImageController.class)) {
            imageControllerStaticMock.when(ImageController::getInstance).thenReturn(mockImageController);
            this.persistImageModel = PersistImageModel.getInstance();
            try {
                this.persistImageModel.loadImage(file);
                doThrow(new FormatNotSupportedException("Format not supported")).when(mockImageController).readImage(file.getAbsolutePath());
            } catch (FormatNotSupportedException | IOException | ImageHeaderUncorrectException ignored) {
            }
        }
    }

    @Test
    void writeImage() {
        try (MockedStatic<ImageController> imageControllerStaticMock = Mockito.mockStatic(ImageController.class)) {
            imageControllerStaticMock.when(ImageController::getInstance).thenReturn(mockImageController);
            this.persistImageModel = PersistImageModel.getInstance();
            File file = new File("testImage.ppm");
            this.persistImageModel.setNewSavingFile(file);
            this.persistImageModel.writeImage();
            verify(mockImageController, times(1)).writeImage(file);
        }
    }

    @Test
    void closeImage() {
        try (MockedStatic<ImageController> imageControllerStaticMock = Mockito.mockStatic(ImageController.class)) {
            imageControllerStaticMock.when(ImageController::getInstance).thenReturn(mockImageController);
            this.persistImageModel = PersistImageModel.getInstance();
            File file = new File("testImage.ppm");
            this.persistImageModel.setNewSavingFile(file);
            this.persistImageModel.setAlreadySave(true);
            this.persistImageModel.closeImage();
            Assertions.assertNull(this.persistImageModel.getCurrentFile());
            Assertions.assertFalse(this.persistImageModel.isAlreadySave());
            verify(mockImageController, times(1)).closeImage();
        }
    }
}