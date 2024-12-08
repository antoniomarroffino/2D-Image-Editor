package ch.supsi.imageEditor.backend.dataaccess.images;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ImageDataAccessTest {

    private ImageDataAccess imageDataAccess;

    @BeforeEach
    public void beforeEach() {
        ImageDataAccess.instance = null;
    }

    @Test
    public void constructor() {
        ImageDataAccess imageDataAccess = new ImageDataAccess();
        Assertions.assertNotNull(imageDataAccess);
        Assertions.assertNotNull(imageDataAccess.getFormatReaderProperties());
    }

    @Test
    public void instance() {
        ImageDataAccess imageDataAccess = ImageDataAccess.getInstance();
        Assertions.assertNotNull(imageDataAccess);
        Assertions.assertNotNull(ImageDataAccess.instance);
        Assertions.assertNotNull(imageDataAccess.getFormatReaderProperties());
    }

    @Test
    public void checkSingleton() {
        ImageDataAccess imageDataAccess1 = ImageDataAccess.getInstance();
        ImageDataAccess imageDataAccess2 = ImageDataAccess.getInstance();
        assertEquals(imageDataAccess1, imageDataAccess2);
    }


    @Test
    public void writeImageTest(@TempDir Path tempDir) {
        AbstractImage image = mock(AbstractImage.class);
        when(image.toString()).thenReturn("test");
        this.imageDataAccess = ImageDataAccess.getInstance();
        File file = tempDir.resolve("temp.ppm").toFile();
        long lastModify = file.lastModified();
        this.imageDataAccess.writeImage(image, file);
        long newModify = file.lastModified();
        Assertions.assertNotEquals(lastModify, newModify);
    }

    @Test
    public void getRecentFilesAndPersistTest() {
        this.imageDataAccess = ImageDataAccess.getInstance();
        this.imageDataAccess.persistRecentFile(List.of(""));
        List<String> recentFiles = this.imageDataAccess.getRecentFiles();
        Assertions.assertNotEquals(0, recentFiles.size());

    }
}