package ch.supsi.imageEditor.backend.business.images;

import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public interface ImageFactoryInterface {
    void readImage(String format) throws FormatNotSupportedException, IOException, ImageHeaderUncorrectException;

    Set<String> getSupportedFormat();

    AbstractImage getImage();

    void writeImage(AbstractImage image, File file);
}
