package ch.supsi.imageEditor.backend.application.language;

public interface LanguageControllerInterface {
    String getCurrentLanguageTag();
    void changeLanguageTag(String languageTag);
}
