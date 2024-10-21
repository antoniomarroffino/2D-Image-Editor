package ch.supsi.imageEditor.backend.business.images.PNM;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageReaderInterface;
import javafx.scene.image.WritableImage;

import java.io.IOException;
import java.util.Scanner;

public abstract class AbstractPnmImage extends AbstractImage implements ImageReaderInterface {
    public void readHeader(Scanner scanner) throws IOException {
        String format = scanner.nextLine();
        if (!format.equals("P1") && !format.equals("P2") && !format.equals("P3")) {
            throw new IOException("Formato non supportato: " + format);
        }

        String line;
        do {
            line = scanner.nextLine();
        } while (line.startsWith("#"));

        String[] dimensions = line.split(" ");
        width = Integer.parseInt(dimensions[0]);
        height = Integer.parseInt(dimensions[1]);
    }

    @Override
    public AbstractImage getImage() {
       return this;
    }
}