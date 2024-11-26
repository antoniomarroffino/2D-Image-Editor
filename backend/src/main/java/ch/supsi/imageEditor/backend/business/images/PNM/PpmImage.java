package ch.supsi.imageEditor.backend.business.images.PNM;

import ch.supsi.imageEditor.backend.business.images.Pixel;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PpmImage extends AbstractPnmImage {
    public PpmImage() {
        format = "P3";
    }

    @Override
    public void read(String filePath) throws IOException, FormatNotSupportedException, ImageHeaderUncorrectException {
        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            readHeader(scanner, filePath);
            this.maxIntensity = Integer.parseInt(scanner.nextLine());
            this.pixel = new Pixel[this.height][this.width];
            try {
                for (int i = 0; i < this.height; i++)
                    for (int j = 0; j < this.width; j++)
                        this.pixel[i][j] = new Pixel(scanner.nextInt() * 255 / this.maxIntensity,
                                scanner.nextInt() * 255 / this.maxIntensity,
                                scanner.nextInt() * 255 / this.maxIntensity);
            } catch (NoSuchElementException ignored) {
                throw new ImageHeaderUncorrectException("The dimensions declared in the header don't match those of the image");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append(this.maxIntensity).append(System.lineSeparator());
        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++)
                stringBuilder.append(this.pixel[i][j].getRed() * this.maxIntensity / 255).append(" ")
                        .append(this.pixel[i][j].getGreen() * this.maxIntensity / 255).append(" ")
                        .append(this.pixel[i][j].getBlue() * this.maxIntensity / 255).append(" ");
        return stringBuilder.toString();
    }
}
