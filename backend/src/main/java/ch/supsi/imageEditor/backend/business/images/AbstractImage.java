package ch.supsi.imageEditor.backend.business.images;

import ch.supsi.imageEditor.backend.business.images.PNM.Pixel;

public abstract class AbstractImage {
    protected int width;
    protected int height;
    protected Pixel[][] pixel = null;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pixel[][] getPixel() {
        return pixel;
    }
}
