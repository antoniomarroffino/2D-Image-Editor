package ch.supsi.imageEditor.backend.business.images;

import ch.supsi.imageEditor.backend.dataaccess.images.ImageDataAccess;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ImageFactoryTest {
    private ImageFactory imageFactory;

    @BeforeEach
    public void beforeEach() {
        ImageFactory.instance = null;
    }

    @Test
    public void constructor() {
        ImageFactory imageFactory = new ImageFactory();
        Assertions.assertNotNull(imageFactory);
    }

    @Test
    public void instance() {
        ImageFactory imageFactory = ImageFactory.getInstance();
        Assertions.assertNotNull(imageFactory);
        Assertions.assertNotNull(ImageFactory.instance);

    }

    @Test
    public void checkSingleton() {
        ImageFactory imageFactory1 = ImageFactory.getInstance();
        ImageFactory imageFactory2 = ImageFactory.getInstance();
        assertEquals(imageFactory1, imageFactory2);
    }


    @Test
    public void readImageTest() {
        this.imageFactory = ImageFactory.getInstance();
        try {
            this.imageFactory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P1/testImageP1.pbm");

            Assertions.assertThrows(FormatNotSupportedException.class, () -> {
                this.imageFactory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P1/testImageP1.xxx");
            });

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void getFileExtensionTest() {
        this.imageFactory = ImageFactory.getInstance();
        String result = this.imageFactory.getFileExtension("./src/test/java/ch/supsi/imageEditor/backend/images/P1/testImageP1.pbm");
        assertEquals("pbm", result);
        String resultEmpty = this.imageFactory.getFileExtension("nonsonounpercorsovalido");
        assertEquals("", resultEmpty);
    }
    @Test
    public void testGetSupportedFormat() {
        Set<String> expectedFormats = Set.of("PBM", "PGM", "PPM");
        this.imageFactory = ImageFactory.getInstance();
        Set<String> supportedFormats = this.imageFactory.getSupportedFormat();
        assertEquals(expectedFormats, supportedFormats);
    }

    @Test
    public void getRecentFilesTest() {
        ImageDataAccess mockImageDataAccess = Mockito.mock(ImageDataAccess.class);
        List<String> list = new ArrayList<>(List.of(""));
        try (MockedStatic<ImageDataAccess> imageDataAccessStaticMock = Mockito.mockStatic(ImageDataAccess.class)) {
            imageDataAccessStaticMock.when(ImageDataAccess::getInstance).thenReturn(mockImageDataAccess);
            when(mockImageDataAccess.getRecentFiles()).thenReturn(list);
            when(mockImageDataAccess.getFormatReaderProperties()).thenReturn(new Properties());
            when(mockImageDataAccess.getFormatExporterProperties()).thenReturn(new Properties());
            this.imageFactory = ImageFactory.getInstance();
        }

        assertEquals(list, this.imageFactory.getRecentFiles());
    }

    @Test
    public void testGetImage() {
        this.imageFactory = ImageFactory.getInstance();
        AbstractImage expectedImage = mock(AbstractImage.class);
        this.imageFactory.currentImage = expectedImage;
        AbstractImage result = this.imageFactory.getImage();
        assertEquals(expectedImage, result);
    }

    @Test
    public void testSetImage() {
        this.imageFactory = ImageFactory.getInstance();
        AbstractImage newImage = mock(AbstractImage.class);
        this.imageFactory.setImage(newImage);
        assertEquals(newImage, this.imageFactory.getImage());
    }

    @Test
    public void testCloseImage() {
        this.imageFactory = ImageFactory.getInstance();
        this.imageFactory.currentImage = mock(AbstractImage.class);
        this.imageFactory.currentImageReader = mock(ImageInterface.class);
        this.imageFactory.closeImage();
        Assertions.assertNull(this.imageFactory.getImage());
    }

    @Test
    public void writeImageTest() {
        AbstractImage image = mock(AbstractImage.class);
        File file = mock(File.class);
        ImageDataAccess mockImageDataAccess = Mockito.mock(ImageDataAccess.class);
        try (MockedStatic<ImageDataAccess> imageDataAccessStaticMock = Mockito.mockStatic(ImageDataAccess.class)) {
            imageDataAccessStaticMock.when(ImageDataAccess::getInstance).thenReturn(mockImageDataAccess);
            when(mockImageDataAccess.getFormatReaderProperties()).thenReturn(new Properties());
            when(mockImageDataAccess.getFormatExporterProperties()).thenReturn(new Properties());
            doNothing().when(mockImageDataAccess).writeImage(image,file);
            this.imageFactory = ImageFactory.getInstance();
            this.imageFactory.writeImage(image,file);
        }

        verify(mockImageDataAccess,times(1)).writeImage(image,file);
    }

    @Test
    void testNoDotInFilePath() {
        this.imageFactory = ImageFactory.getInstance();
        String filePath = "filename";
        String result = this.imageFactory.getFileExtension(filePath);
        assertEquals("", result);
    }

    @Test
    void testDotIsLastCharacter() {
        this.imageFactory = ImageFactory.getInstance();
        String filePath = "filename.";
        String result = this.imageFactory.getFileExtension(filePath);
        assertEquals("", result);
    }

    @Test
    void testValidExtension() {
        this.imageFactory = ImageFactory.getInstance();
        String filePath = "filename.txt";
        String result = this.imageFactory.getFileExtension(filePath);
        assertEquals("txt", result);
    }

    @Test
    void testMultipleDotsInFilePath() {
        this.imageFactory = ImageFactory.getInstance();
        String filePath = "archive.tar.gz";
        String result = this.imageFactory.getFileExtension(filePath);
        assertEquals("gz", result);
    }

    @Test
    void testDotAsOnlyCharacter() {
        this.imageFactory = ImageFactory.getInstance();
        String filePath = ".";
        String result = this.imageFactory.getFileExtension(filePath);
        assertEquals("", result);
    }

    @Test
    void testEmptyFilePath() {
        this.imageFactory = ImageFactory.getInstance();
        String filePath = "";
        String result = this.imageFactory.getFileExtension(filePath);
        assertEquals("", result);
    }

}