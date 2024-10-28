package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class InvertColor implements Operation {

    @Override
    public AbstractImage doOperation(AbstractImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] pixels = image.getPixelMatrix();

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                Pixel pixel = pixels[x][y];
                pixel.setRed(255 - pixel.getRed());
                pixel.setGreen(255 - pixel.getGreen());
                pixel.setBlue(255 - pixel.getBlue());
            }
        image.setPixel(pixels);
        return image;
    }
}
