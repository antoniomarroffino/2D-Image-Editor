package ch.supsi.imageEditor.frontend.model.language;

import java.util.ResourceBundle;
import java.util.Set;

public interface LanguageModelInterface {
    Set<String> getSupportedLanguages();
    void changeLanguage(String languageKey);
    ResourceBundle getCurrentResourceBundle();
}
