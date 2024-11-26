package ch.supsi.imageEditor.backend.business.images.PNM;

import ch.supsi.imageEditor.backend.business.images.Pixel;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PbmImage extends AbstractPnmImage {
    public PbmImage() {
        format = "P1";
    }

    @Override
    public void read(String filePath) throws IOException, FormatNotSupportedException, ImageHeaderUncorrectException {
        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            readHeader(scanner, filePath);
            this.maxIntensity = 1;
            this.pixel = new Pixel[this.height][this.width];
            try {
                for (int i = 0; i < this.height; i++)
                    for (int j = 0; j < this.width; j++)
                        this.pixel[i][j] = new Pixel(scanner.nextInt() == 1 ? 255 : 0);
            } catch (NoSuchElementException ignored) {
                throw new ImageHeaderUncorrectException("The dimensions declared in the header don't match those of the image");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++)
                stringBuilder.append(this.pixel[i][j].getRed() == 255 ? 1 : 0).append(" ");
        return stringBuilder.toString();
    }
}
