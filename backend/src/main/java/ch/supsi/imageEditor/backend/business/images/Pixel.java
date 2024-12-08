package ch.supsi.imageEditor.backend.business.images;

public class Pixel {
    private int red;
    private int green;
    private int blue;

    public Pixel(int pixelColor) {
        this.red = pixelColor;
        this.green = pixelColor;
        this.blue = pixelColor;
    }

    public Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
