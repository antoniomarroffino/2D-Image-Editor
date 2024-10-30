package ch.supsi.imageEditor.backend;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class WriterTest {
    private AbstractImage p1;
    private AbstractImage p2;
    private AbstractImage p3;

    @BeforeEach
    public void setUp() {
        ImageFactory factory = ImageFactory.getInstance();
        try {
            factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/dimensions/P1.pbm");
            p1 = factory.getImage();
            factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/dimensions/P2.pgm");
            p2 = factory.getImage();
            factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/dimensions/P3.ppm");
            p3 = factory.getImage();
        } catch (FormatNotSupportedException | IOException | ImageHeaderUncorrectException ignored) {
            ;
        }
    }

    @Test
    public void testPBMWriteImage() {
        String result = "P1\r\n# Image edited by 2D Image Editor\r\n4 4\r\n0 1 0 1 1 0 1 0 0 1 0 1 1 0 1 0";;
        assertEquals(result.trim(), p1.toString().trim());
    }

    @Test
    public void testPGMWriteImage() {
        String result = "P2\r\n# Image edited by 2D Image Editor\r\n5 5\r\n255\r\n0 50 100 150 200 50 100 150 200 255 100 150 200 255 200 150 200 255 200 150 200 255 200 150 100";
        assertEquals(result.trim(), p2.toString().trim());
    }

    @Test
    public void testPPMWriteImage() {
        String result = "P3\r\n# Image edited by 2D Image Editor\r\n3 2\r\n255\r\n255 0 0 0 255 0 0 0 255 255 255 0 0 255 255 255 0 255";
        assertEquals(result.trim(), p3.toString().trim());
    }
}
