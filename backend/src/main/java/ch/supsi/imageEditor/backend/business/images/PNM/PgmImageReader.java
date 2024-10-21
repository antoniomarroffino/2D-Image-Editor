package ch.supsi.imageEditor.backend.business.images.PNM;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PgmImageReader extends AbstractPnmImage {
    private int maxVal;

    @Override
    public void read(String filePath) throws IOException {
        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            readHeader(scanner);
            maxVal = Integer.parseInt(scanner.nextLine());
            pixel = new Pixel[height][width];
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    pixel[i][j] = new Pixel(scanner.nextInt() * 255 / maxVal);
        }
    }
}
