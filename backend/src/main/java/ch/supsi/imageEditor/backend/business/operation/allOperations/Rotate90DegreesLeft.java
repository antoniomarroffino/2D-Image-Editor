package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class Rotate90DegreesLeft implements Operation {
    public AbstractImage doOperation(AbstractImage image) {
        int newWidth = image.getHeight();
        int newHeight = image.getWidth();
        Pixel[][] rotatedPixels = new Pixel[newWidth][newHeight];
        for (int x = 0; x < newHeight; x++)
            for (int y = 0; y < newWidth; y++)
                rotatedPixels[y][newHeight - x - 1] = image.getPixel(x, y);
        image.setPixel(rotatedPixels);
        image.setWidth(newWidth);
        image.setHeight(newHeight);
        return image;
    }
}
