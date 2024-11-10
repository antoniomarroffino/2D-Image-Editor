package ch.supsi.imageEditor.backend.business.language;

import ch.supsi.imageEditor.backend.dataaccess.language.LanguageDataAccess;

public class LanguageModel implements LanguageModelInterface {
    protected static LanguageModel instance;

    private final LanguageDataAccess languageDataAccess;

    protected LanguageModel() {
        this.languageDataAccess = LanguageDataAccess.getInstance();
    }

    public static LanguageModel getInstance() {
        return instance == null ? instance = new LanguageModel() : instance;
    }

    @Override
    public String getCurrentLanguageTag() {
        return this.languageDataAccess.getCurrentLanguageTag();
    }

    @Override
    public void changeLanguage(String languageTag) {
        this.languageDataAccess.changeLanguage(languageTag);
    }
}
