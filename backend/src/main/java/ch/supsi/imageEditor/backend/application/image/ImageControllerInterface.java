package ch.supsi.imageEditor.backend.application.image;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ImageControllerInterface {
    void readImage(String path) throws FormatNotSupportedException, IOException, ImageHeaderUncorrectException;

    void writeImage(File file);

    void writeImage(File sourceFile, File destinationfile);

    AbstractImage getImage();

    Set<String> getSupportedFormat();

    List<String> getRecentFiles();

    void closeImage();
}
