package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.ImageFactoryTest;
import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FlipSideToSideTest {
    private final ImageFactory factory = new ImageFactoryTest();

    @Test
    public void testP1FlipSideToSide() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P1/testImageP1.pbm");
            AbstractImage P1Image = this.factory.getImage();
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P1/testImageP1flipSideToSide.pbm");
            AbstractImage expectedP1Image = this.factory.getImage();
            AbstractImage flippedP1Image = new FlipSideToSide().doOperation(P1Image);
            Assertions.assertEquals(expectedP1Image, flippedP1Image);
        } catch (Exception e) {
            Assertions.fail("Test failed due to " + e.getMessage());
        }
    }

    @Test
    public void testP2FlipSideToSide() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P2/testImageP2.pgm");
            AbstractImage P2Image = this.factory.getImage();
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P2/testImageP2flipSideToSide.pgm");
            AbstractImage expectedP2Image = this.factory.getImage();
            AbstractImage flippedP2Image = new FlipSideToSide().doOperation(P2Image);
            Assertions.assertEquals(expectedP2Image, flippedP2Image);
        } catch (Exception e) {
            Assertions.fail("Test failed due to " + e.getMessage());
        }
    }

    @Test
    public void testP3FlipSideToSide() {
        try {
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P3/testImageP3.ppm");
            AbstractImage P3Image = this.factory.getImage();
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P3/testImageP3flipSideToSide.ppm");
            AbstractImage expectedP3Image = this.factory.getImage();
            AbstractImage flippedP3Image = new FlipSideToSide().doOperation(P3Image);
            Assertions.assertEquals(expectedP3Image, flippedP3Image);
        } catch (Exception e) {
            Assertions.fail("Test failed due to " + e.getMessage());
        }
    }
}
