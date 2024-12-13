package ch.supsi.imageEditor.backend.dataaccess.language;

import ch.supsi.imageEditor.backend.dataaccess.provider.DataAccessProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class LanguageDataAccessTest {
    private LanguageDataAccess languageDataAccess;

    @BeforeEach
    public void beforeEach() {
        LanguageDataAccess.instance = null;
    }

    @Test
    public void constructor() {
        LanguageDataAccess languageDataAccess = new LanguageDataAccess();
        Assertions.assertNotNull(languageDataAccess);
    }

    @Test
    public void instance() {
        LanguageDataAccess languageDataAccess = LanguageDataAccess.getInstance();
        Assertions.assertNotNull(languageDataAccess);
        Assertions.assertNotNull(LanguageDataAccess.instance);
    }

    @Test
    public void checkSingleton() {
        LanguageDataAccess languageDataAccess1 = LanguageDataAccess.getInstance();
        LanguageDataAccess languageDataAccess2 = LanguageDataAccess.getInstance();
        assertEquals(languageDataAccess1, languageDataAccess2);
    }

    @Test
    public void testGetCurrentLanguageTag(@TempDir Path tempDir) {
        DataAccessProvider mockDataAccessProvider = mock(DataAccessProvider.class);
        try (MockedStatic<DataAccessProvider> mockedDataAccessProvider = mockStatic(DataAccessProvider.class)) {
            mockedDataAccessProvider.when(DataAccessProvider::getInstance).thenReturn(mockDataAccessProvider);
            when(mockDataAccessProvider.getDefaultPreferencesPath()).thenReturn("/default-user-preferences.properties");
            when(mockDataAccessProvider.getUserHomeDirectory()).thenReturn(tempDir.toString());
            when(mockDataAccessProvider.getPreferencesDirectory()).thenReturn(".preferences");
            when(mockDataAccessProvider.getPreferencesFile()).thenReturn("preferences.properties");
            this.languageDataAccess = LanguageDataAccess.getInstance();
            String result = "en-US";
            String currentLanguageTag = this.languageDataAccess.getCurrentLanguageTag();
            Assertions.assertEquals(result, currentLanguageTag);
            String currentLanguageTag2 = this.languageDataAccess.getCurrentLanguageTag();
            Assertions.assertEquals(result, currentLanguageTag2);
            LanguageDataAccess.instance = null;
            this.languageDataAccess = LanguageDataAccess.getInstance();
            String currentLanguageTag3 = this.languageDataAccess.getCurrentLanguageTag();
            Assertions.assertEquals(result, currentLanguageTag3);
        }
    }

    @Test
    public void testChangeLanguage(@TempDir Path tempDir) {
        DataAccessProvider mockDataAccessProvider = mock(DataAccessProvider.class);
        try (MockedStatic<DataAccessProvider> mockedDataAccessProvider = mockStatic(DataAccessProvider.class)) {
            mockedDataAccessProvider.when(DataAccessProvider::getInstance).thenReturn(mockDataAccessProvider);
            when(mockDataAccessProvider.getDefaultPreferencesPath()).thenReturn("/default-user-preferences.properties");
            when(mockDataAccessProvider.getUserHomeDirectory()).thenReturn(tempDir.toString());
            when(mockDataAccessProvider.getPreferencesDirectory()).thenReturn(".preferences");
            when(mockDataAccessProvider.getPreferencesFile()).thenReturn("preferences.properties");
            this.languageDataAccess = LanguageDataAccess.getInstance();

            this.languageDataAccess.changeLanguage("it-CH");
            this.languageDataAccess.getCurrentLanguageTag();
            Path preferencesFile = tempDir.resolve(".preferences/preferences.properties");
            String content = Files.readString(preferencesFile);
            assertTrue(content.contains("language-tag=en-US"));

            this.languageDataAccess.changeLanguage("it-CH");
            String content2 = Files.readString(preferencesFile);
            assertTrue(content2.contains("language-tag=it-CH"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

