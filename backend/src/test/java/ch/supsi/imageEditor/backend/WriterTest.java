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
        String result = "P1" + System.lineSeparator()
                + "# Image edited by 2D Image Editor" + System.lineSeparator()
                + "4 4" + System.lineSeparator()
                + "0 1 0 1 1 0 1 0 0 1 0 1 1 0 1 0 ";
        assertEquals(result, p1.toString());
    }

    @Test
    public void testPGMWriteImage() {
        String result = "P2" + System.lineSeparator()
                + "# Image edited by 2D Image Editor" + System.lineSeparator()
                + "5 5" + System.lineSeparator()
                + "255" + System.lineSeparator()
                + "0 50 100 150 200 50 100 150 200 255 100 150 200 255 200 150 200 255 200 150 200 255 200 150 100 ";
        assertEquals(result, p2.toString());
    }

    @Test
    public void testPPMWriteImage() {
        String result = "P3" + System.lineSeparator()
                + "# Image edited by 2D Image Editor" + System.lineSeparator()
                + "3 2" + System.lineSeparator()
                + "255" + System.lineSeparator()
                + "255 0 0 0 255 0 0 0 255 255 255 0 0 255 255 255 0 255 ";
        assertEquals(result, p3.toString());
    }
}
