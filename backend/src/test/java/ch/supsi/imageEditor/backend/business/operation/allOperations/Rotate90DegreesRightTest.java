package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.ImageFactoryTest;
import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Rotate90DegreesRightTest {
    private final ImageFactory factory = new ImageFactoryTest();

    @Test
    public void testP1Rotate90DegreesRight() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P1/testImageP1.pbm");
            AbstractImage P1Image = this.factory.getImage();
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P1/testImageP1rotateLeft.pbm");
            AbstractImage expectedP1Image = this.factory.getImage();
            AbstractImage rotateRightP1Image = new Rotate90DegreesRight().doOperation(P1Image);
            Assertions.assertEquals(expectedP1Image, rotateRightP1Image);
        } catch (Exception e) {
            Assertions.fail("Test failed due to " + e.getMessage());
        }
    }

    @Test
    public void testP2Rotate90DegreesRight() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P2/testImageP2.pgm");
            AbstractImage P2Image = this.factory.getImage();
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P2/testImageP2rotateLeft.pgm");
            AbstractImage expectedP2Image = this.factory.getImage();
            AbstractImage rotateRightP2Image = new Rotate90DegreesRight().doOperation(P2Image);
            Assertions.assertEquals(expectedP2Image, rotateRightP2Image);
        } catch (Exception e) {
            Assertions.fail("Test failed due to " + e.getMessage());
        }
    }

    @Test
    public void testP3Rotate90DegreesRight() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P3/testImageP3.ppm");
            AbstractImage P3Image = this.factory.getImage();
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P3/testImageP3rotateLeft.ppm");
            AbstractImage expectedP3Image = this.factory.getImage();
            AbstractImage rotateRightP3Image = new Rotate90DegreesRight().doOperation(P3Image);
            Assertions.assertEquals(expectedP3Image, rotateRightP3Image);
        } catch (Exception e) {
            Assertions.fail("Test failed due to " + e.getMessage());
        }
    }
}

