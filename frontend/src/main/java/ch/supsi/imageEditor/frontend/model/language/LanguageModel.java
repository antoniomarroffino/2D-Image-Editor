package ch.supsi.imageEditor.frontend.model.language;

import ch.supsi.imageEditor.backend.application.language.LanguageController;
import ch.supsi.imageEditor.backend.application.language.LanguageControllerInterface;
import ch.supsi.imageEditor.frontend.exception.LanguageNotSupportedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class LanguageModel implements LanguageModelInterface {
    private static LanguageModel instance = null;
    private final LanguageControllerInterface languageController;

    private final Map<String, String> supportedLanguagesKeyTag;
    private final Properties supportedLanguagesProperties;
    private String currentLanguageTag;
    private final ResourceBundle resourceBundle;

    private static final String languageBundlePath = "i18n.labels";
    private static final String supportedLanguagesPath = "/i18n/supported_languages.properties";

    private LanguageModel() {
        this.supportedLanguagesProperties = this.getSupportedLanguagesProperties();
        this.supportedLanguagesKeyTag = this.getSupportedLanguagesKeyTag();
        this.languageController = LanguageController.getInstance();

        String languageTag = this.languageController.getCurrentLanguageTag();
        checkLanguageTagSupported(languageTag);
        resourceBundle = this.createCurrentResourceBundle();
    }

    public static LanguageModel getInstance() {
        return instance == null ? instance = new LanguageModel() : instance;
    }

    private ResourceBundle createCurrentResourceBundle() {
        return ResourceBundle.getBundle(languageBundlePath, Locale.forLanguageTag(this.currentLanguageTag));
    }

    @Override
    public ResourceBundle getCurrentResourceBundle() {
        return this.resourceBundle;
    }

    private Properties getSupportedLanguagesProperties() {
        Properties languageProperties = new Properties();
        try {
            InputStream supportedLanguageTagsStream = this.getClass().getResourceAsStream(supportedLanguagesPath);
            languageProperties.load(supportedLanguageTagsStream);
        } catch (IOException e) {
            System.err.println("ERROR: Unable to load supported languages properties from " + supportedLanguagesPath);
        }
        return languageProperties;
    }

    private Map<String, String> getSupportedLanguagesKeyTag() {
        Map<String, String> languageKeyTag = new HashMap<>();
        for (String languageKey : this.supportedLanguagesProperties.keySet().stream().map(String::valueOf).toList())
            languageKeyTag.put(languageKey, this.supportedLanguagesProperties.getProperty(languageKey));
        return languageKeyTag;
    }

    private void checkLanguageTagSupported(String languageTag) {
        try {
            if (!this.supportedLanguagesKeyTag.containsValue(languageTag)) {
                this.currentLanguageTag = this.supportedLanguagesKeyTag.values().stream().findFirst().orElseThrow();
                throw new LanguageNotSupportedException("Language tag " + languageTag + " not supported!\nEnglish is set as default language.");
            } else
                this.currentLanguageTag = languageTag;
        } catch (LanguageNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Set<String> getSupportedLanguages() {
        return new HashSet<>(this.supportedLanguagesKeyTag.keySet());
    }

    @Override
    public void changeLanguage(String languageKey) {
        String languageTag = this.supportedLanguagesKeyTag.get(languageKey);
        if (languageTag != null)
            this.languageController.changeLanguageTag(languageTag);
    }
}
