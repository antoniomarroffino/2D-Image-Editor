package ch.supsi.imageEditor.backend.business.images.PNM;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageReaderInterface;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.util.Scanner;

public abstract class AbstractPnmImage extends AbstractImage implements ImageReaderInterface {
    public void readHeader(Scanner scanner, String filepath) throws FormatNotSupportedException, ImageHeaderUncorrectException {
        String format = scanner.nextLine();
        if (!format.equals("P1") && !format.equals("P2") && !format.equals("P3"))
            throw new FormatNotSupportedException("Format " + format + " is not supported");

        String extension = this.getFileExtension(filepath);
        if ((format.equals("P1") && !extension.equals("PBM")) || (format.equals("P2") && !extension.equals("PGM")) || (format.equals("P3") && !extension.equals("PPM")))
            throw new ImageHeaderUncorrectException(extension + " not correspond to " + format + " format in the image header");

        String line;
        do
            line = scanner.nextLine();
        while (line.startsWith("#")); // Avoid comments

        String[] dimensions = line.split(" ");
        width = Integer.parseInt(dimensions[0]);
        height = Integer.parseInt(dimensions[1]);
    }

    @Override
    public AbstractImage getImage() {
        return this;
    }

    private String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filePath.length() - 1)
            return "";
        return filePath.substring(lastDotIndex + 1).toUpperCase();
    }

}