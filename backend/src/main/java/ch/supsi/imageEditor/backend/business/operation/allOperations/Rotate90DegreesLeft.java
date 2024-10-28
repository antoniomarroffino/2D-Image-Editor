package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class Rotate90DegreesLeft implements Operation {
    @Override
    public AbstractImage doOperation(AbstractImage image) {
        System.out.println(this.getClass().getSimpleName());

        int newWidth = image.getHeight();
        int newHeight = image.getWidth();
        Pixel[][] rotatedPixels = new Pixel[newWidth][newHeight];
        for (int x = 0; x < newHeight; x++) {
            for (int y = 0; y < newWidth; y++) {
                rotatedPixels[newWidth - y - 1][x] = image.getPixel(x, y);
            }
        }
        image.setPixel(rotatedPixels);
        image.setWidth(newWidth);
        image.setHeight(newHeight);
        System.out.println("ROTATE90LEFT");
        return image;
    }
}
