package ch.supsi.imageEditor.backend.business.language;

import ch.supsi.imageEditor.backend.dataaccess.language.LanguageDataAccess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

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
    }

    @Test
    public void instance() {
        LanguageModel languageModel = LanguageModel.getInstance();
        Assertions.assertNotNull(languageModel);
        Assertions.assertNotNull(LanguageModel.instance);
    }

    @Test
    public void checkSingleton() {
        LanguageModel languageModel1 = LanguageModel.getInstance();
        LanguageModel languageModel2 = LanguageModel.getInstance();
        Assertions.assertEquals(languageModel1, languageModel2);
    }

    @Test
    public void getCurrentLanguageTagTest() {
        LanguageDataAccess mockLanguageDataAccess = mock(LanguageDataAccess.class);
        String expectedLanguageTag = "en";
        when(mockLanguageDataAccess.getCurrentLanguageTag()).thenReturn(expectedLanguageTag);
        try (MockedStatic<LanguageDataAccess> mockedStatic = mockStatic(LanguageDataAccess.class)) {
            mockedStatic.when(LanguageDataAccess::getInstance).thenReturn(mockLanguageDataAccess);
            languageModel = LanguageModel.getInstance();
            String actualLanguageTag = languageModel.getCurrentLanguageTag();
            Assertions.assertEquals(expectedLanguageTag, actualLanguageTag);
            verify(mockLanguageDataAccess, times(1)).getCurrentLanguageTag();
        }
    }

    @Test
    public void changeLanguageTest() {
        LanguageDataAccess mockLanguageDataAccess = mock(LanguageDataAccess.class);
        String languageTag = "fr";
        try (MockedStatic<LanguageDataAccess> mockedStatic = mockStatic(LanguageDataAccess.class)) {
            mockedStatic.when(LanguageDataAccess::getInstance).thenReturn(mockLanguageDataAccess);
            languageModel = LanguageModel.getInstance();
            languageModel.changeLanguage(languageTag);
            verify(mockLanguageDataAccess, times(1)).changeLanguage(languageTag);
        }
    }
}