package ch.supsi.imageEditor.frontend.model.language;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class LanguageModel implements LanguageModelInterface {
    private static LanguageModel instance = null;
    private final Set<String> supportedLanguagesKeys;

    private static final String supportedLanguagesPath = "/i18n/supported_languages.properties";
    private final Properties supportedLanguagesProperties;

    private LanguageModel() {
        this.supportedLanguagesProperties = this.getSupportedLanguagesProperties();
        this.supportedLanguagesKeys = this.getSupportedLanguagesKeys();
    }

    public static LanguageModel getInstance() {
        return instance == null ? instance = new LanguageModel() : instance;
    }

    private Properties getSupportedLanguagesProperties() {
        Properties languageProperties = new Properties();
        try{
            InputStream supportedLanguageTagsStream = this.getClass().getResourceAsStream(supportedLanguagesPath);
            languageProperties.load(supportedLanguageTagsStream);
        }catch (IOException e){
            System.err.println("ERROR: Unable to load supported languages properties from " + supportedLanguagesPath);
        }
        return languageProperties;
    }

    private Set<String> getSupportedLanguagesKeys() {
        return supportedLanguagesProperties.keySet().stream().map(String::valueOf).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSupportedLanguages() {
        return new HashSet<>(supportedLanguagesKeys);
    }
}
