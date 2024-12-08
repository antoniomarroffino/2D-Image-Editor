package ch.supsi.imageEditor.backend.business.images.PNM;


import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AbstractPnmImageTest {

    private PbmImage pbmImage;
    private PgmImage pgmImage;
    private PpmImage ppmImage;

    @BeforeEach
    public void setUp() {
        pbmImage = new PbmImage();
        pgmImage = new PgmImage();
        ppmImage = new PpmImage();
    }

    @Test
    public void testReadHeaderWithValidPbm() {
        String testFilePath = "src/test/java/ch/supsi/imageEditor/backend/images/P1/P1test.pbm";
        assertDoesNotThrow(() -> pbmImage.read(testFilePath));
    }

    @Test
    public void testReadHeaderWithValidPgm() {
        String testFilePath = "src/test/java/ch/supsi/imageEditor/backend/images/P2/P2test.pgm";
        assertDoesNotThrow(() -> pgmImage.read(testFilePath));
    }

    @Test
    public void testReadHeaderWithInvalidPgm() {
        String testFilePath = "src/test/java/ch/supsi/imageEditor/backend/images/P2/P2testError.pgm";
        assertThrows(ImageHeaderUncorrectException.class, () -> pgmImage.read(testFilePath));
    }

    @Test
    public void testReadHeaderWithValidPpm() {
        String testFilePath = "src/test/java/ch/supsi/imageEditor/backend/images/P3/P3test.ppm";
        assertDoesNotThrow(() -> ppmImage.read(testFilePath));
    }

    @Test
    public void testReadHeaderWithInvalidPpm() {
        String testFilePath = "src/test/java/ch/supsi/imageEditor/backend/images/P3/P3testError.ppm";
        assertThrows(ImageHeaderUncorrectException.class, () -> ppmImage.read(testFilePath));
    }

    @Test
    public void testFormatNotSupported() {
        String testFilePath = "src/test/java/ch/supsi/imageEditor/backend/images/P1/P4testError.pbm";
        assertThrows(FormatNotSupportedException.class, () -> pbmImage.read(testFilePath));
    }

    @Test
    public void testFormatNotSupportedWithoutDot() {
        String testFilePath = "src/test/java/ch/supsi/imageEditor/backend/images/P1/P1test";
        assertThrows(ImageHeaderUncorrectException.class, () -> pbmImage.read(testFilePath));
    }

    @Test
    public void testFormatNotSupportedInAnotherFormat() {
        String testFilePath = "src/test/java/ch/supsi/imageEditor/backend/images/testImageInAnotherFormat.png";
        assertThrows(FormatNotSupportedException.class, () -> pbmImage.read(testFilePath));
    }

    @Test
    public void testFormatNotSupportedInAnotherFormat2() {
        String testFilePath = "src/test/java/ch/supsi/imageEditor/backend/images/P3/P3testFormatNotCorrect.pgm";
        assertThrows(ImageHeaderUncorrectException.class, () -> ppmImage.read(testFilePath));
    }

    @Test
    public void testFormatNotSupportedInAnotherFormat3() {
        String testFilePath = "src/test/java/ch/supsi/imageEditor/backend/images/P2/P2testFormatNotCorrect.ppm";
        assertThrows(ImageHeaderUncorrectException.class, () -> pgmImage.read(testFilePath));
    }
}
