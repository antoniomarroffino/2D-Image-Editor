package ch.supsi.imageEditor.backend.business.images;

import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;

public interface ImageReaderFactoryInterface {
    void readImage(String format) throws FormatNotSupportedException;
}
