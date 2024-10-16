package ch.supsi.imageEditor.backend.application.language;

import ch.supsi.imageEditor.backend.business.language.LanguageModel;

public class LanguageController implements LanguageControllerInterface {
    private static LanguageController instance = null;
    private final LanguageModel languageModel;

    private LanguageController() {
        this.languageModel = LanguageModel.getInstance();
    }

    public static LanguageController getInstance() {
        return instance == null ? instance = new LanguageController() : instance;
    }

    @Override
    public String getCurrentLanguageTag() {
        return this.languageModel.getCurrentLanguageTag();
    }

    @Override
    public void changeLanguageTag(String languageTag) {
        this.languageModel.changeLanguage(languageTag);
    }
}
