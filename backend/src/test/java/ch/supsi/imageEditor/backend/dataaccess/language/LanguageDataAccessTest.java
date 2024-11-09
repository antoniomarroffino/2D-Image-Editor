package ch.supsi.imageEditor.backend.dataaccess.language;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class LanguageDataAccessTest {

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
        Assertions.assertEquals(languageDataAccess1, languageDataAccess2);
    }
}

/*
Istanza protetta all'interno della classe, al posto che privata, così da poter assegnare null
Costruttore protected al posto che private
 */