package ch.supsi.imageEditor.backend.business.images;

import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.IOException;
import java.util.Set;

public interface ImageReaderFactoryInterface {
    void readImage(String format) throws FormatNotSupportedException, IOException, ImageHeaderUncorrectException;

    Set<String> getSupportedFormat();

    AbstractImage getImage();

    void setImage(AbstractImage image);
}
