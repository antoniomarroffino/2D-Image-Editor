package ch.supsi.imageEditor.backend.application.image;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;

import java.io.IOException;
import java.util.Set;

public interface ImageControllerInterface {
    void loadImage(String path) throws FormatNotSupportedException, IOException;

    AbstractImage getImage();

    Set<String> getSupportedFormat();
}
