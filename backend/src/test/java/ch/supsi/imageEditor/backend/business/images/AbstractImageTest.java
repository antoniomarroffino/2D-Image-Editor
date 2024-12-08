package ch.supsi.imageEditor.backend.business.images;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractImageTest {

    private static class ConcreteImage extends AbstractImage {
        public ConcreteImage(int width, int height) {
            this.width = width;
            this.height = height;
            this.pixel = new Pixel[height][width];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    this.pixel[i][j] = new Pixel(0);
                }
            }
        }
    }

    private ConcreteImage image;

    @BeforeEach
    public void setUp() {
        image = new ConcreteImage(5, 5);
    }

    @Test
    public void testGetWidth() {
        assertEquals(5, image.getWidth());
    }

    @Test
    public void testGetHeight() {
        assertEquals(5, image.getHeight());
    }

    @Test
    public void testSetWidth() {
        image.setWidth(10);
        assertEquals(10, image.getWidth());
    }

    @Test
    public void testSetHeight() {
        image.setHeight(10);
        assertEquals(10, image.getHeight());
    }

    @Test
    public void testGetPixel() {
        Pixel pixel = image.getPixel(2, 2);
        assertNotNull(pixel);
        assertEquals(0, pixel.getRed());
    }

    @Test
    public void testGetPixelNegativeX() {
        Pixel pixel = image.getPixel(-1, 2);
        assertNull(pixel, "Expected null for x < 0");
    }

    @Test
    public void testGetPixelXOutOfUpperBound() {
        Pixel pixel = image.getPixel(5, 2);
        assertNull(pixel, "Expected null for x >= height");
    }

    @Test
    public void testGetPixelNegativeY() {
        Pixel pixel = image.getPixel(2, -1);
        assertNull(pixel, "Expected null for y < 0");
    }

    @Test
    public void testGetPixelYOutOfUpperBound() {
        Pixel pixel = image.getPixel(2, 5);
        assertNull(pixel, "Expected null for y >= width");
    }

    @Test
    public void testGetPixelOutOfBounds() {
        Pixel pixel = image.getPixel(10, 10);
        assertNull(pixel);
    }

    @Test
    public void testSetPixel() {
        Pixel[][] newPixels = new Pixel[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                newPixels[i][j] = new Pixel(255);
            }
        }
        image.setPixel(newPixels);
        assertEquals(255, image.getPixel(0, 0).getRed());
    }

    @Test
    public void getPixelTest(){
        Pixel pixel = image.getPixel(2, 2);
        assertNotNull(pixel);
    }

    @Test
    public void toStringNotNullTest() {
        String result = image.toString();
        assertNotNull(result, "The toString method should not return null");
    }

    @Test
    public void testGetPixelMatrixNotNull() {
        Pixel[][] pixelMatrix = image.getPixelMatrix();
        assertNotNull(pixelMatrix, "Expected the pixel matrix to not be null");
    }

    @Test
    public void testGetPixelMatrixCorrectDimensions() {
        Pixel[][] pixelMatrix = image.getPixelMatrix();
        assertEquals(5, pixelMatrix.length, "Expected the height of the matrix to match the image height");
        assertEquals(5, pixelMatrix[0].length, "Expected the width of the matrix to match the image width");
    }

    @Test
    public void testGetPixelMatrixElements() {
        Pixel[][] pixelMatrix = image.getPixelMatrix();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertNotNull(pixelMatrix[i][j], "Expected each pixel in the matrix to not be null");
                assertEquals(0, pixelMatrix[i][j].getRed(), "Expected the red value of each pixel to be initialized to 0");
            }
        }
    }

    @Test
    public void maxIntensityTest(){
        int maxIntensity= 255;
        image.setMaxIntensity(maxIntensity);
        Assertions.assertEquals(maxIntensity, image.getMaxIntensity(), "Expected the max intensity to equal to 255");
    }

    @Test
    public void testGetFormat() {
        image.format = "JPEG";
        assertEquals("JPEG", image.getFormat(), "Expected the format to be 'JPEG'");
    }

    @Test
    public void testGetUpExport() {
        List<String> upExport = List.of("ExportOption1", "ExportOption2");
        image.upExport = upExport;
        assertNotNull(image.getUpExport(), "Expected upExport list to not be null");
        assertEquals(upExport, image.getUpExport(), "Expected upExport list to match the mocked data");
    }

    @Test
    public void testGetDownExport() {
        List<String> downExport = List.of("OptionA", "OptionB");
        image.downExport = downExport;
        assertNotNull(image.getDownExport(), "Expected downExport list to not be null");
        assertEquals(downExport, image.getDownExport(), "Expected downExport list to match the mocked data");
    }
}
