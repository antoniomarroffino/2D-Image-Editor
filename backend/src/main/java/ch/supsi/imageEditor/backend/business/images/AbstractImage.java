package ch.supsi.imageEditor.backend.business.images;

import java.util.List;

public abstract class AbstractImage {
    protected String format;
    protected int width;
    protected int height;
    protected Pixel[][] pixel = null;
    protected int maxIntensity;
    protected List<String> downExport;
    protected List<String> upExport;

    public String getFormat() {
        return format;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Pixel getPixel(int x, int y) {
        if (x < 0 || x >= this.height || y < 0 || y >= this.width)
            return null;
        return this.pixel[x][y];
    }

    public Pixel[][] getPixelMatrix() {
        return this.pixel;
    }

    public void setPixel(Pixel[][] pixel) {
        this.pixel = pixel;
    }

    public int getMaxIntensity() {
        return maxIntensity;
    }

    public void setMaxIntensity(int maxIntensity) {
        this.maxIntensity = maxIntensity;
    }

    public List<String> getUpExport() {
        return upExport;
    }

    public List<String> getDownExport() {
        return downExport;
    }

    @Override
    public String toString() {
        return this.format + System.lineSeparator() +
                "# Image edited by 2D Image Editor" + System.lineSeparator() +
                this.width + " " + this.height + System.lineSeparator();
    }
}
