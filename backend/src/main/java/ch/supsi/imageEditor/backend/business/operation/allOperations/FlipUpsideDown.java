package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class FlipUpsideDown implements Operation {
    @Override
    public AbstractImage doOperation(AbstractImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] pixels = image.getPixelMatrix();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height / 2; y++) {
                Pixel temp = pixels[x][y];
                pixels[x][y] = pixels[x][height - y - 1];
                pixels[x][height - y - 1] = temp;
            }
        }
        image.setPixel(pixels);
        return image;
    }
}
