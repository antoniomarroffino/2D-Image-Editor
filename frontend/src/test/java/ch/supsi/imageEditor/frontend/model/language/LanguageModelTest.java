package ch.supsi.imageEditor.frontend.model.language;

import ch.supsi.imageEditor.backend.application.language.LanguageController;
import ch.supsi.imageEditor.frontend.exception.LanguageNotSupportedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class LanguageModelTest {
    private LanguageModel languageModel;

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

    @Test
    void testGetCurrentLanguage() {
        LanguageController mockLanguageController = Mockito.mock(LanguageController.class);
        String languageTag = "en";
        try (MockedStatic<LanguageController> languageControllerStaticMock = Mockito.mockStatic(LanguageController.class)) {
            languageControllerStaticMock.when(LanguageController::getInstance).thenReturn(mockLanguageController);
            when(mockLanguageController.getCurrentLanguageTag()).thenReturn(languageTag);
            this.languageModel = LanguageModel.getInstance();
            String currentLanguage = this.languageModel.getCurrentLanguage();
            Assertions.assertEquals("english", currentLanguage);
        }
    }

    @Test
    void testCheckLanguageTagSupported() {
        String unsupportedTag = "de-DE";
        String defaultTag = "en-US";
        LanguageController mockLanguageController = Mockito.mock(LanguageController.class);
        try (MockedStatic<LanguageController> languageControllerStaticMock = Mockito.mockStatic(LanguageController.class)) {
            languageControllerStaticMock.when(LanguageController::getInstance).thenReturn(mockLanguageController);
            when(mockLanguageController.getCurrentLanguageTag()).thenReturn(defaultTag);
            this.languageModel = LanguageModel.getInstance();
            this.languageModel.changeLanguage(unsupportedTag);
            Assertions.assertEquals(defaultTag, mockLanguageController.getCurrentLanguageTag());
        } catch (LanguageNotSupportedException e) {
            Assertions.assertTrue(e.getMessage().contains("not supported"));
        }
    }

    @Test
    void testChangeLanguageToSameLanguage() {
        String currentLanguageKey = "english";
        String currentLanguageTag = "en-US";
        LanguageController mockLanguageController = Mockito.mock(LanguageController.class);
        try (MockedStatic<LanguageController> languageControllerStaticMock = mockStatic(LanguageController.class)) {
            languageControllerStaticMock.when(LanguageController::getInstance).thenReturn(mockLanguageController);
            when(mockLanguageController.getCurrentLanguageTag()).thenReturn(currentLanguageTag);
            languageModel = LanguageModel.getInstance();
            languageModel.changeLanguage(currentLanguageKey);
            verify(mockLanguageController, times(0)).changeLanguageTag("it-CH");
            Assertions.assertEquals(currentLanguageTag, mockLanguageController.getCurrentLanguageTag());
        }
    }
}