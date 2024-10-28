package ch.supsi.imageEditor.backend.business.images;

import java.util.Arrays;

public abstract class AbstractImage {
    protected String format;
    protected int width;
    protected int height;
    protected Pixel[][] pixel = null;
    protected int maxIntensity;

    public String getFormat() {
        return format;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Pixel[][] getPixel() {
        return pixel;
    }

    public Pixel getPixel(int x, int y) {
        if(x < 0 || x >= this.width || y < 0 || y >= this.height)
            return null;
        return this.pixel[x][y];
    }

    public int getMaxIntensity() {
        return this.maxIntensity;
    }

    @Override
    public String toString() {
        return this.format + System.lineSeparator() +
                "# Image edited by 2D Image Editor" + System.lineSeparator() +
                this.width + " " + this.height + System.lineSeparator();
    }
}
