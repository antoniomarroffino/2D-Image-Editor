package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class Rotate90DegreesRight implements Operation {
    @Override
    public AbstractImage doOperation(AbstractImage image) {
        int newWidth = image.getHeight();
        int newHeight = image.getWidth();
        Pixel[][] rotatedPixels = new Pixel[newHeight][newWidth];
        for (int r = 0; r < newWidth; r++) {
            for (int c = 0; c < newHeight; c++) {
                rotatedPixels[c][newWidth - r - 1] = image.getPixel(r, c);
            }
        }
        image.setPixel(rotatedPixels);
        image.setWidth(newWidth);
        image.setHeight(newHeight);
        return image;
    }
}
