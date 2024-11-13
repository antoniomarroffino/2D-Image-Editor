package ch.supsi.imageEditor.frontend.model.language;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LanguageModelTest {
    @BeforeEach
    public void beforeEach() {
        LanguageModel.instance = null;
    }

    @Test
    public void constructor() {
        LanguageModel languageModel = new LanguageModel();
        Assertions.assertNotNull(languageModel);
        Assertions.assertNotNull(languageModel.getLanguageController());
        Assertions.assertNotNull(languageModel.getCurrentResourceBundle());
        Assertions.assertNotNull(languageModel.getLanguagesProperties());
        Assertions.assertNotNull(languageModel.getSupportedLanguagesKeyTag());
    }

    @Test
    public void instance() {
        LanguageModel languageModel = LanguageModel.getInstance();
        Assertions.assertNotNull(languageModel);
        Assertions.assertNotNull(LanguageModel.instance);
        Assertions.assertNotNull(languageModel.getLanguageController());
        Assertions.assertNotNull(languageModel.getCurrentResourceBundle());
        Assertions.assertNotNull(languageModel.getLanguagesProperties());
        Assertions.assertNotNull(languageModel.getSupportedLanguagesKeyTag());
    }

    @Test
    public void checkSingleton() {
        LanguageModel languageModel1 = LanguageModel.getInstance();
        LanguageModel languageModel2 = LanguageModel.getInstance();
        Assertions.assertEquals(languageModel1, languageModel2);
    }
}