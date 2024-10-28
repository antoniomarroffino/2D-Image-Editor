package ch.supsi.imageEditor.backend.dataaccess.images;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;

import java.io.File;
import java.util.Properties;

public interface ImageDataAccessInterface {
    Properties getFormatReaderProperties();

    void writeImage(AbstractImage image, File file);
}
