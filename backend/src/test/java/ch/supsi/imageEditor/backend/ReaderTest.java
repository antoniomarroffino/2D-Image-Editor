package ch.supsi.imageEditor.backend;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ReaderTest {
    private ImageFactory factory;

    @BeforeEach
    public void setUp() {
        this.factory = new ImageFactoryTest();
    }

    @Test
    public void testFormatNotExists() {
        Assertions.assertThrows(FormatNotSupportedException.class, () -> this.factory.readImage("test.png"));
    }

    @Test
    public void testFileNotExists() {
        Assertions.assertThrows(IOException.class, () -> this.factory.readImage("test.ppm"));
    }

    @Test
    public void testUncorrectHeader() {
        Assertions.assertThrows(ImageHeaderUncorrectException.class, () -> this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/dimensions/P1.ppm"));
    }

    @Test
    public void testUncorrectDeclaredDimensions() {
        Assertions.assertThrows(ImageHeaderUncorrectException.class, () -> this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/dimensions/P1_uncorrect-header.pbm"));
    }

    @Test
    public void testPBMReadImage() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P1.pbm");
            AbstractImage image = this.factory.getImage();
            Assertions.assertEquals(4, image.getWidth());
            Assertions.assertEquals(4, image.getHeight());
        } catch (IOException | FormatNotSupportedException | ImageHeaderUncorrectException ignored) {
            ;
        }
    }

    @Test
    public void testPGMReadImage() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/dimensions/P2.pgm");
            AbstractImage image = this.factory.getImage();
            Assertions.assertEquals(5, image.getWidth());
            Assertions.assertEquals(5, image.getHeight());
        } catch (IOException | FormatNotSupportedException | ImageHeaderUncorrectException ignored) {
            ;
        }
    }

    @Test
    public void testPPMReadImage() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/dimensions/P3.ppm");
            AbstractImage image = this.factory.getImage();
            Assertions.assertEquals(3, image.getWidth());
            Assertions.assertEquals(2, image.getHeight());
        } catch (IOException | FormatNotSupportedException | ImageHeaderUncorrectException ignored) {
            ;
        }
    }

}
