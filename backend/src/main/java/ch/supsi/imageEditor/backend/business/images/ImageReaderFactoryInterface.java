package ch.supsi.imageEditor.backend.business.images;

import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;

import java.util.Set;

public interface ImageReaderFactoryInterface {
    void readImage(String format) throws FormatNotSupportedException;

    Set<String> getSupportedFormat();
}
