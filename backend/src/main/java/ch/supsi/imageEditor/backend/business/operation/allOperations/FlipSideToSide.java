package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class FlipSideToSide implements Operation {
    @Override
    public AbstractImage doOperation(AbstractImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] flippedPixels = new Pixel[height][width];
        for (int r = 0; r < height; r++)
            for (int c = 0; c < width; c++) {
                flippedPixels[r][width - 1 - c] = image.getPixel(r, c);
            }

        image.setPixel(flippedPixels);
        return image;
    }
}
