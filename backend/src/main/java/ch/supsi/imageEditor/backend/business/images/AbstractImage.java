package ch.supsi.imageEditor.backend.business.images;

public abstract class AbstractImage {
    protected int width;
    protected int height;
    protected Pixel[][] pixel = null;
    protected int maxIntensity;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pixel getPixel(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return null;
        return pixel[x][y];
    }

    public Pixel[][] getPixelMatrix() {
        return this.pixel;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPixel(Pixel[][] pixel) {
        this.pixel = pixel;
    }

    public void setMaxIntensity(int maxIntensity) {
        this.maxIntensity = maxIntensity;
    }

    public int getMaxIntensity() {
        return maxIntensity;
    }
}
