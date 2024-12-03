package ch.supsi.imageEditor.backend.business.images;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PixelTest {

    @Test
    public void testConstructorWithSingleColor() {
        int pixelColor = 128;
        Pixel pixel = new Pixel(pixelColor);

        assertEquals(pixelColor, pixel.getRed());
        assertEquals(pixelColor, pixel.getGreen());
        assertEquals(pixelColor, pixel.getBlue());
    }

    @Test
    public void testConstructorWithRGB() {
        int red = 255, green = 100, blue = 50;
        Pixel pixel = new Pixel(red, green, blue);

        assertEquals(red, pixel.getRed());
        assertEquals(green, pixel.getGreen());
        assertEquals(blue, pixel.getBlue());
    }

    @Test
    public void testSettersAndGetters() {
        Pixel pixel = new Pixel(0);

        pixel.setRed(255);
        pixel.setGreen(200);
        pixel.setBlue(100);

        assertEquals(255, pixel.getRed());
        assertEquals(200, pixel.getGreen());
        assertEquals(100, pixel.getBlue());
    }

    @Test
    public void testInvalidColorValues() {
        Pixel pixel = new Pixel(256, -1, 500);

        assertFalse(pixel.getRed() >= 0 && pixel.getRed() <= 255);
        assertFalse(pixel.getGreen() >= 0 && pixel.getGreen() <= 255);
        assertFalse(pixel.getBlue() >= 0 && pixel.getBlue() <= 255);
    }
}

