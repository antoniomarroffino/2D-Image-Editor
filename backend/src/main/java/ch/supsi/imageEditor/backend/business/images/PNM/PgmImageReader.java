package ch.supsi.imageEditor.backend.business.images.PNM;

import ch.supsi.imageEditor.backend.business.images.Pixel;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PgmImageReader extends AbstractPnmImage {
    private int maxVal;

    @Override
    public void read(String filePath) throws IOException, FormatNotSupportedException, ImageHeaderUncorrectException {
        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            readHeader(scanner, filePath);
            maxVal = Integer.parseInt(scanner.nextLine());
            pixel = new Pixel[height][width];
            try {
                for (int i = 0; i < height; i++)
                    for (int j = 0; j < width; j++)
                        pixel[i][j] = new Pixel(scanner.nextInt() * 255 / maxVal);
            } catch (NoSuchElementException ignored) {
                throw new ImageHeaderUncorrectException("The dimensions declared in the header don't match those of the image");
            }
        }
    }
}
