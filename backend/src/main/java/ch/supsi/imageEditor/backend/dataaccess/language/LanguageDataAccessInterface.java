package ch.supsi.imageEditor.backend.dataaccess.language;

public interface LanguageDataAccessInterface {
    String getCurrentLanguageTag();

    void changeLanguage(String languageTag);
}
