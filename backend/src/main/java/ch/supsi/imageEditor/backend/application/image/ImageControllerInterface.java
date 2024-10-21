package ch.supsi.imageEditor.backend.application.image;

import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;

import java.util.Set;

public interface ImageControllerInterface {
    void displayImage(String path) throws FormatNotSupportedException;

    Set<String> getSupportedFormat();
}
