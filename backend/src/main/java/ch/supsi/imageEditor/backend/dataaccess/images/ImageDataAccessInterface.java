package ch.supsi.imageEditor.backend.dataaccess.images;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public interface ImageDataAccessInterface {
    Properties getFormatReaderProperties();

    void writeImage(AbstractImage image, File file);

    List<String> getRecentFiles();

    void persistRecentFile(List<String> recentFiles);
}
