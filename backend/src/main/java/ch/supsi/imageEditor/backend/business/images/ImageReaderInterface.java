package ch.supsi.imageEditor.backend.business.images;

import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.IOException;

public interface ImageReaderInterface {
    void read(String filePath) throws IOException, FormatNotSupportedException, ImageHeaderUncorrectException;

    AbstractImage getImage();
}
