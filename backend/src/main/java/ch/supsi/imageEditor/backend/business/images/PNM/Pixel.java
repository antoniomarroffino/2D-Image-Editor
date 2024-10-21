package ch.supsi.imageEditor.backend.business.images.PNM;

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

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
