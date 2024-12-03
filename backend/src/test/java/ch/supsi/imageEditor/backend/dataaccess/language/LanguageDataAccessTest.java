package ch.supsi.imageEditor.backend.dataaccess.language;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}

