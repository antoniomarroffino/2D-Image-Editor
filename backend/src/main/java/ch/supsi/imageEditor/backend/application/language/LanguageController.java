package ch.supsi.imageEditor.backend.application.language;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.application.observer.NotificationService;
import ch.supsi.imageEditor.backend.application.observer.NotificationServiceInterface;
import ch.supsi.imageEditor.backend.business.language.LanguageModel;

public class LanguageController implements LanguageControllerInterface {
    protected static LanguageController instance = null;

    private final LanguageModel languageModel;
    private final NotificationServiceInterface notificationService;

    protected LanguageController() {
        this.languageModel = LanguageModel.getInstance();
        this.notificationService = NotificationService.getInstance();
    }

    public static LanguageController getInstance() {
        return instance == null ? instance = new LanguageController() : instance;
    }

    LanguageModel getLanguageModel() {
        return this.languageModel;
    }

    NotificationServiceInterface getNotificationService() {
        return this.notificationService;
    }

    @Override
    public String getCurrentLanguageTag() {
        return this.languageModel.getCurrentLanguageTag();
    }

    @Override
    public void changeLanguageTag(String languageTag) {
        this.languageModel.changeLanguage(languageTag);
        this.notificationService.notify(EventType.CHANGE_LANGUAGE);
    }
}
