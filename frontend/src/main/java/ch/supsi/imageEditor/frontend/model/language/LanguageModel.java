package ch.supsi.imageEditor.frontend.model.language;

import ch.supsi.imageEditor.backend.application.language.LanguageController;
import ch.supsi.imageEditor.backend.application.language.LanguageControllerInterface;
import ch.supsi.imageEditor.frontend.exception.LanguageNotSupportedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class LanguageModel implements LanguageModelInterface {
    private static LanguageModel instance = null;
    private final Set<String> supportedLanguagesKeys;
    private final Set<String> supportedLanguagesTags;
    private final LanguageControllerInterface languageController;

    private static final String supportedLanguagesPath = "/i18n/supported_languages.properties";
    private final Properties supportedLanguagesProperties;
    private String currentLanguageTag;

    private LanguageModel() {
        this.supportedLanguagesProperties = this.getSupportedLanguagesProperties();
        this.supportedLanguagesKeys = this.getSupportedLanguagesKeys();
        this.supportedLanguagesTags = this.getSupportedLanguagesTags();
        this.languageController = LanguageController.getInstance();
        String languageTag = this.languageController.getCurrentLanguageTag();
        checkLanguageTagSupported(languageTag);
        System.out.println(this.currentLanguageTag);
    }

    public static LanguageModel getInstance() {
        return instance == null ? instance = new LanguageModel() : instance;
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

    private Set<String> getSupportedLanguagesKeys() {
        return supportedLanguagesProperties.keySet().stream().map(String::valueOf).collect(Collectors.toSet());
    }

    private Set<String> getSupportedLanguagesTags() {
        return supportedLanguagesProperties.values().stream().map(String::valueOf).collect(Collectors.toSet());
    }

    private void checkLanguageTagSupported(String languageTag) {
        try {
            if(!supportedLanguagesTags.contains(languageTag)) {
                this.currentLanguageTag = supportedLanguagesTags.stream().findFirst().orElseThrow();
                throw new LanguageNotSupportedException("Language tag " + languageTag + " not supported!\nEnglish is set as default language.");
            } else
                this.currentLanguageTag = languageTag;
        } catch (LanguageNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Set<String> getSupportedLanguages() {
        return new HashSet<>(supportedLanguagesKeys);
    }
}
