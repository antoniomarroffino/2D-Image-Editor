package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class FlipSideToSide implements Operation {
    @Override
    public AbstractImage doOperation(AbstractImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] pixels = image.getPixelMatrix();
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width / 2; x++) {
                Pixel temp = pixels[x][y];
                pixels[x][y] = pixels[width - x - 1][y];
                pixels[width - x - 1][y] = temp;
            }
        image.setPixel(pixels);
        return image;
    }
}
