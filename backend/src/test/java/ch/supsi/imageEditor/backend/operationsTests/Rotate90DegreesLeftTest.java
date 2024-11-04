package ch.supsi.imageEditor.backend.operationsTests;

import ch.supsi.imageEditor.backend.ImageFactoryTest;
import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import ch.supsi.imageEditor.backend.business.operation.allOperations.Rotate90DegreesLeft;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Rotate90DegreesLeftTest {
    private final ImageFactory factory = new ImageFactoryTest();

    @Test
    public void testP1Rotate90DegreesLeft() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P1/testImageP1.pbm");
            AbstractImage P1Image = this.factory.getImage();
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P1/testImageP1rotateLeft.pbm");
            AbstractImage expectedP1Image = this.factory.getImage();
            AbstractImage rotateLeftP1Image = new Rotate90DegreesLeft().doOperation(P1Image);
            Assertions.assertEquals(expectedP1Image, rotateLeftP1Image);
        } catch (Exception e) {
            Assertions.fail("Test failed due to " + e.getMessage());
        }
    }

    @Test
    public void testP2Rotate90DegreesLeft() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P2/testImageP2.pgm");
            AbstractImage P2Image = this.factory.getImage();
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P2/testImageP2rotateLeft.pgm");
            AbstractImage expectedP2Image = this.factory.getImage();
            AbstractImage rotateLeftP2Image = new Rotate90DegreesLeft().doOperation(P2Image);
            Assertions.assertEquals(expectedP2Image, rotateLeftP2Image);
        } catch (Exception e) {
            Assertions.fail("Test failed due to " + e.getMessage());
        }
    }

    @Test
    public void testP3Rotate90DegreesLeft() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P3/testImageP3.ppm");
            AbstractImage P3Image = this.factory.getImage();
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P3/testImageP3rotateLeft.ppm");
            AbstractImage expectedP3Image = this.factory.getImage();
            AbstractImage rotateLeftP3Image = new Rotate90DegreesLeft().doOperation(P3Image);
            Assertions.assertEquals(expectedP3Image, rotateLeftP3Image);
        } catch (Exception e) {
            Assertions.fail("Test failed due to " + e.getMessage());
        }
    }
}
