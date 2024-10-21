package ch.supsi.imageEditor.backend.business.images;

import javafx.scene.image.WritableImage;

import java.io.IOException;

public interface ImageReaderInterface {
    void read(String filePath) throws IOException;

    AbstractImage getImage();
}
