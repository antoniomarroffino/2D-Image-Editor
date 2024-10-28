package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class InvertColor implements Operation {

    @Override
    public AbstractImage doOperation(AbstractImage image) {
        System.out.println(getClass().getSimpleName());

        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] pixels = image.getPixelMatrix();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel pixel = pixels[x][y];
                int maxIntensity = image.getMaxIntensity();

                pixel.setRed(maxIntensity - pixel.getRed());
                pixel.setGreen(maxIntensity - pixel.getGreen());
                pixel.setBlue(maxIntensity - pixel.getBlue());
            }
        }
        image.setPixel(pixels);
        return image;
    }
}
