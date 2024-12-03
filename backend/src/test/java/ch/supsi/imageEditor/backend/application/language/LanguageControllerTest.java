package ch.supsi.imageEditor.backend.application.language;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.application.observer.NotificationService;
import ch.supsi.imageEditor.backend.business.language.LanguageModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class LanguageControllerTest {

    private LanguageController languageController;
    @BeforeEach
    public void beforeEach() {
        LanguageController.instance = null;
    }

    @Test
    public void constructor() {
        LanguageController languageController = new LanguageController();
        Assertions.assertNotNull(languageController);
    }

    @Test
    public void instance() {
        LanguageController languageController = LanguageController.getInstance();
        Assertions.assertNotNull(languageController);
        Assertions.assertNotNull(LanguageController.instance);
    }

    @Test
    public void checkSingleton() {
        LanguageController languageController1 = LanguageController.getInstance();
        LanguageController languageController2 = LanguageController.getInstance();
        Assertions.assertEquals(languageController1, languageController2);
    }

    @Test
    public void getCurrentLanguageTagTest() {
        LanguageModel mockLanguageModel = Mockito.mock(LanguageModel.class);
        NotificationService mockNotificationService = Mockito.mock(NotificationService.class);

        try (MockedStatic<LanguageModel> languageModelStaticMock = Mockito.mockStatic(LanguageModel.class);
             MockedStatic<NotificationService> notificationServiceStaticMock = Mockito.mockStatic(NotificationService.class)) {

            languageModelStaticMock.when(LanguageModel::getInstance).thenReturn(mockLanguageModel);
            notificationServiceStaticMock.when(NotificationService::getInstance).thenReturn(mockNotificationService);

            this.languageController = LanguageController.getInstance();
            when(mockLanguageModel.getCurrentLanguageTag()).thenReturn("en-US");

            String result = this.languageController.getCurrentLanguageTag();
            Assertions.assertEquals("en-US", result);
            verify(mockLanguageModel).getCurrentLanguageTag();
        }
    }

    @Test
    public void changeLanguageTagTest() {
        LanguageModel mockLanguageModel = Mockito.mock(LanguageModel.class);
        NotificationService mockNotificationService = Mockito.mock(NotificationService.class);

        try (MockedStatic<LanguageModel> languageModelStaticMock = Mockito.mockStatic(LanguageModel.class);
             MockedStatic<NotificationService> notificationServiceStaticMock = Mockito.mockStatic(NotificationService.class)) {

            languageModelStaticMock.when(LanguageModel::getInstance).thenReturn(mockLanguageModel);
            notificationServiceStaticMock.when(NotificationService::getInstance).thenReturn(mockNotificationService);

            this.languageController = LanguageController.getInstance();

            String languageTag = "fr-FR";

            this.languageController.changeLanguageTag(languageTag);

            verify(mockLanguageModel).changeLanguage(languageTag);
            verify(mockNotificationService).notify(EventType.CHANGE_LANGUAGE);
        }
    }


}