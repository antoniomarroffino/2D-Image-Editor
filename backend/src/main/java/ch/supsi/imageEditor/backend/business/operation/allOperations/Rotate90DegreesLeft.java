package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class Rotate90DegreesLeft implements Operation {
    @Override
    public AbstractImage doOperation(AbstractImage image) {
        int newWidth = image.getHeight();
        int newHeight = image.getWidth();
        Pixel[][] rotatedPixels = new Pixel[newHeight][newWidth];
        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {
                rotatedPixels[newHeight - y - 1][x] = image.getPixel(y,x);
            }
        }
        image.setPixel(rotatedPixels);
        image.setWidth(newWidth);
        image.setHeight(newHeight);
        return image;
    }
}
