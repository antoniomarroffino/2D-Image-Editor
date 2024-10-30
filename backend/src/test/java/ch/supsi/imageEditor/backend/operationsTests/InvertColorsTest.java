package ch.supsi.imageEditor.backend.operationsTests;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import ch.supsi.imageEditor.backend.business.operation.allOperations.FlipSideToSide;
import ch.supsi.imageEditor.backend.business.operation.allOperations.InvertColors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvertColorsTest {

    private final ImageFactory factory = ImageFactory.getInstance();

    @Test
    public void testP1InvertColors() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P1/testImageP1.pbm");
            AbstractImage P1Image = this.factory.getImage();
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P1/testImageP1inverted.pbm");
            AbstractImage expectedP1Image = this.factory.getImage();
            AbstractImage invertedP1Image = new InvertColors().doOperation(P1Image);
            Assertions.assertEquals(expectedP1Image, invertedP1Image);
        } catch (Exception e) {
            Assertions.fail("Test failed due to " + e.getMessage());
        }
    }

    @Test
    public void testP2InvertColors() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P2/testImageP2.pgm");
            AbstractImage P2Image = this.factory.getImage();
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P2/testImageP2inverted.pgm");
            AbstractImage expectedP2Image = this.factory.getImage();
            AbstractImage invertedP2Image = new FlipSideToSide().doOperation(P2Image);
            Assertions.assertEquals(expectedP2Image, invertedP2Image);
        } catch (Exception e) {
            Assertions.fail("Test failed due to " + e.getMessage());
        }
    }

    @Test
    public void testP3InvertColors() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P3/testImageP3.ppm");
            AbstractImage P3Image = this.factory.getImage();
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P3/testImageP3inverted.ppm");
            AbstractImage expectedP3Image = this.factory.getImage();
            AbstractImage invertedP3Image = new InvertColors().doOperation(P3Image);
            Assertions.assertEquals(expectedP3Image, invertedP3Image);
        } catch (Exception e) {
            Assertions.fail("Test failed due to " + e.getMessage());
        }
    }


}
