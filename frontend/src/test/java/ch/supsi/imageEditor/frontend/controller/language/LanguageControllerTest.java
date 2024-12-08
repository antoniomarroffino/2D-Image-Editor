package ch.supsi.imageEditor.frontend.controller.language;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.language.LanguageModel;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LanguageControllerTest {
    private final DataView dataViewMock1 = Mockito.mock(DataView.class);
    private final DataView dataViewMock2 = Mockito.mock(DataView.class);

    private LanguageController languageController;

    @BeforeEach
    public void beforeEach() {
        LanguageController.instance = null;
    }

    @Test
    public void constructor() {
        languageController = new LanguageController();
        Assertions.assertNotNull(languageController);
        Assertions.assertNotNull(languageController.getLanguageModel());
        Assertions.assertNotNull(languageController.getPubSubModel());
    }

    @Test
    public void instance() {
        languageController = LanguageController.getInstance();
        Assertions.assertNotNull(languageController);
        Assertions.assertNotNull(LanguageController.instance);
        Assertions.assertNotNull(languageController.getLanguageModel());
        Assertions.assertNotNull(languageController.getPubSubModel());
    }

    @Test
    public void checkSingleton() {
        LanguageController languageController1 = LanguageController.getInstance();
        LanguageController languageController2 = LanguageController.getInstance();
        Assertions.assertEquals(languageController1, languageController2);
    }

    @Test
    public void testChangeLanguage() {
        LanguageModel mockLanguageModel = Mockito.mock(LanguageModel.class);
        Component componentMock = Mockito.mock(Component.class);
        String languageKey = "it";
        Mockito.when(componentMock.getId()).thenReturn(languageKey);
        try (MockedStatic<LanguageModel> languageModelStaticMock = Mockito.mockStatic(LanguageModel.class)) {
            languageModelStaticMock.when(LanguageModel::getInstance).thenReturn(mockLanguageModel);
            languageController = LanguageController.getInstance();

            languageController.changeLanguage(componentMock);
            verify(mockLanguageModel, times(1)).changeLanguage(languageKey);
        }
    }

    @Test
    public void testUpdate() {
        languageController = LanguageController.getInstance();
        languageController.initialize(Arrays.asList(dataViewMock1, dataViewMock2));

        languageController.update(EventType.CHANGE_LANGUAGE);
        verify(dataViewMock1, times(1)).update(EventType.CHANGE_LANGUAGE);
        verify(dataViewMock2, times(1)).update(EventType.CHANGE_LANGUAGE);
    }
}