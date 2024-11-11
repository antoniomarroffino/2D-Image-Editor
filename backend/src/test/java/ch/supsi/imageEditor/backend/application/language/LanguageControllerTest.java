package ch.supsi.imageEditor.backend.application.language;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LanguageControllerTest {
    @BeforeEach
    public void beforeEach() {
        LanguageController.instance = null;
    }

    @Test
    public void constructor() {
        LanguageController languageController = new LanguageController();
        Assertions.assertNotNull(languageController);
        Assertions.assertNotNull(languageController.getLanguageModel());
        Assertions.assertNotNull(languageController.getNotificationService());
    }

    @Test
    public void instance() {
        LanguageController languageController = LanguageController.getInstance();
        Assertions.assertNotNull(languageController);
        Assertions.assertNotNull(LanguageController.instance);
        Assertions.assertNotNull(languageController.getLanguageModel());
        Assertions.assertNotNull(languageController.getNotificationService());
    }

    @Test
    public void checkSingleton() {
        LanguageController languageController1 = LanguageController.getInstance();
        LanguageController languageController2 = LanguageController.getInstance();
        Assertions.assertEquals(languageController1, languageController2);
    }
}