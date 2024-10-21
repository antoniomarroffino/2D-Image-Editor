package ch.supsi.imageEditor.backend.business.images;

import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import javafx.scene.image.WritableImage;

import java.io.IOException;
import java.util.Set;

public interface ImageReaderFactoryInterface {
    void readImage(String format) throws FormatNotSupportedException, IOException;

    Set<String> getSupportedFormat();

    AbstractImage getImage();
}
