package ch.supsi.imageEditor.backend.business.images.PNM;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageInterface;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.util.Scanner;

public abstract class AbstractPnmImage extends AbstractImage implements ImageInterface {
    public void readHeader(Scanner scanner, String filepath) throws FormatNotSupportedException, ImageHeaderUncorrectException {
        String format = scanner.nextLine();
        this.format = format.toUpperCase();
        if (!this.format.equals("P1") && !this.format.equals("P2") && !this.format.equals("P3"))
            throw new FormatNotSupportedException("Format " + this.format + " is not supported");

        String extension = this.getFileExtension(filepath);
        if ((this.format.equals("P1") && !extension.equals("PBM")) || (this.format.equals("P2") && !extension.equals("PGM")) || (this.format.equals("P3") && !extension.equals("PPM")))
            throw new ImageHeaderUncorrectException(extension + " not correspond to " + this.format + " format in the image header");

        String line;
        do
            line = scanner.nextLine();
        while (line.startsWith("#")); // Avoid comments

        String[] dimensions = line.split(" ");
        this.width = Integer.parseInt(dimensions[0]);
        this.height = Integer.parseInt(dimensions[1]);
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