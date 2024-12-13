package ch.supsi.imageEditor.backend.dataaccess.images;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.dataaccess.provider.DataAccessProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImageDataAccessTest {

    private ImageDataAccess imageDataAccess;

    @BeforeEach
    void setUp() {
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
    public void testLoadFilePathCreatesDirectoryIfNotExists(@TempDir Path tempDir) {
        DataAccessProvider mockDataAccessProvider = mock(DataAccessProvider.class);
        try (MockedStatic<DataAccessProvider> mockedDataAccessProvider = mockStatic(DataAccessProvider.class)) {
            mockedDataAccessProvider.when(DataAccessProvider::getInstance).thenReturn(mockDataAccessProvider);
            when(mockDataAccessProvider.getFormatReaderPropertiesPath()).thenReturn("/format-reader.properties");
            when(mockDataAccessProvider.getFormatExporterPropertiesPath()).thenReturn("/format-exporter.properties");
            when(mockDataAccessProvider.getUserHomeDirectory()).thenReturn(tempDir.toString());
            when(mockDataAccessProvider.getPreferencesDirectory()).thenReturn(".preferences");
            when(mockDataAccessProvider.getRecentFiles()).thenReturn("recentFiles.txt");
            Path preferencesPath = tempDir.resolve(".preferences");
            assertFalse(Files.exists(preferencesPath));
            this.imageDataAccess = ImageDataAccess.getInstance();
            assertTrue(Files.exists(preferencesPath));
        }
    }

    @Test
    public void testWriteImage(@TempDir Path tempDir) {
        AbstractImage mockImage = mock(AbstractImage.class);
        when(mockImage.toString()).thenReturn("Mock Image Content");
        DataAccessProvider mockDataAccessProvider = mock(DataAccessProvider.class);
        try (MockedStatic<DataAccessProvider> mockedDataAccessProvider = mockStatic(DataAccessProvider.class)) {
            mockedDataAccessProvider.when(DataAccessProvider::getInstance).thenReturn(mockDataAccessProvider);
            when(mockDataAccessProvider.getFormatReaderPropertiesPath()).thenReturn("/format-reader.properties");
            when(mockDataAccessProvider.getFormatExporterPropertiesPath()).thenReturn("/format-exporter.properties");
            when(mockDataAccessProvider.getUserHomeDirectory()).thenReturn(tempDir.toString());
            when(mockDataAccessProvider.getPreferencesDirectory()).thenReturn(".preferences");
            when(mockDataAccessProvider.getRecentFiles()).thenReturn("recentFiles.txt");
            this.imageDataAccess = ImageDataAccess.getInstance();
            File tempFile = tempDir.resolve("testImage.pp").toFile();
            imageDataAccess.writeImage(mockImage, tempFile);
            String fileContent = Files.readString(tempFile.toPath());
            assertEquals("Mock Image Content", fileContent);

        } catch (IOException e) {
            Assertions.fail(e);
        }
    }
}
