package ch.supsi.imageEditor.backend.business.language;

public interface LanguageModelInterface {
    String getCurrentLanguageTag();

    void changeLanguage(String languageTag);
}
