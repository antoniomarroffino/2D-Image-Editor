package ch.supsi.imageEditor.backend.dataaccess.images;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.dataaccess.AbstractDataAccessTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ImageDataAccessTest extends AbstractDataAccessTest {

    private ImageDataAccess imageDataAccess;

    @BeforeEach
    public void beforeEach() {
        ImageDataAccess.instance = null;
    }

    @Test
    public void constructor() {
        super.setUp();
        ImageDataAccess imageDataAccess = new ImageDataAccess();
        Assertions.assertNotNull(imageDataAccess);
        Assertions.assertNotNull(imageDataAccess.getFormatReaderProperties());
        super.tearDown();
    }

    @Test
    public void instance() {
        super.setUp();
        ImageDataAccess imageDataAccess = ImageDataAccess.getInstance();
        Assertions.assertNotNull(imageDataAccess);
        Assertions.assertNotNull(ImageDataAccess.instance);
        Assertions.assertNotNull(imageDataAccess.getFormatReaderProperties());
        super.tearDown();
    }

    @Test
    public void checkSingleton() {
        super.setUp();
        ImageDataAccess imageDataAccess1 = ImageDataAccess.getInstance();
        ImageDataAccess imageDataAccess2 = ImageDataAccess.getInstance();
        assertEquals(imageDataAccess1, imageDataAccess2);
        super.tearDown();
    }

    @Test
    public void testLoadingOpenRecentFile() {
        super.setUp();
        this.imageDataAccess = ImageDataAccess.getInstance();
        assertTrue(Files.exists(preferencesPath));
        super.tearDown();
    }

    @Test
    public void writeImageTest(@TempDir Path tempDir) {
        super.setUp();
        AbstractImage image = mock(AbstractImage.class);
        when(image.toString()).thenReturn("test");
        this.imageDataAccess = ImageDataAccess.getInstance();
        File file = tempDir.resolve("temp.ppm").toFile();
        long lastModify = file.lastModified();
        this.imageDataAccess.writeImage(image, file);
        long newModify = file.lastModified();
        Assertions.assertNotEquals(lastModify, newModify);
        super.tearDown();
    }

    @Test
    public void getRecentFilesAndPersistTest() {
        super.setUp();
        this.imageDataAccess = ImageDataAccess.getInstance();
        this.imageDataAccess.persistRecentFile(List.of(""));
        List<String> recentFiles = this.imageDataAccess.getRecentFiles();
        Assertions.assertNotEquals(0, recentFiles.size());
        super.tearDown();
    }
}