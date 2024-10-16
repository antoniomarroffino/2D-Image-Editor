package ch.supsi.imageEditor.backend.dataaccess.language;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class LanguageDataAccess implements LanguageDataAccessInterface {
    private static LanguageDataAccess instance;

    private static final String defaultPreferencesPath = "/default-user-preferences.properties";
    private static final String userHomeDirectory = System.getProperty("user.home");
    private static final String preferencesDirectory = ".userpreferences";
    private static final String preferencesFile = "preferences.properties";

    private Properties userPreferences;

    private LanguageDataAccess() {
    }

    public static LanguageDataAccess getInstance() {
        return instance == null ? instance = new LanguageDataAccess() : instance;
    }

    @Override
    public String getCurrentLanguageTag() {
        if (this.userPreferences != null)
            return this.getLanguageTagFromProperties();

        if (this.userPreferencesFileExists()) {
            this.userPreferences = this.loadPreferences(this.getUserPreferencesFilePath());
            return this.getLanguageTagFromProperties();
        }

        this.userPreferences = this.loadDefaultPreferences();
        this.createUserPreferencesFile(userPreferences);

        return this.getLanguageTagFromProperties();
    }

    @Override
    public void changeLanguage(String languageTag) {
        String content = "language-tag=" + languageTag;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(this.getUserPreferencesFilePath())))) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private boolean userPreferencesFileExists() {
        return Files.exists(this.getUserPreferencesFilePath());
    }

    private Path getUserPreferencesFilePath() {
        return Path.of(userHomeDirectory, preferencesDirectory, preferencesFile);
    }

    private Properties loadPreferences(Path path) {
        Properties preferences = new Properties();
        try {
            preferences.load(new FileInputStream(String.valueOf(path)));
        } catch (IOException ignoredForDemoPurposes) {
            return null;
        }
        return preferences;
    }

    private Properties loadDefaultPreferences() {
        Properties defaultPreferences = new Properties();
        try {
            InputStream defaultPreferencesStream = this.getClass().getResourceAsStream(defaultPreferencesPath);
            defaultPreferences.load(defaultPreferencesStream);
        } catch (IOException ignored) {
            ;
        }
        return defaultPreferences;
    }

    private Path getUserPreferencesDirectoryPath() {
        return Path.of(userHomeDirectory, preferencesDirectory);
    }

    private void createUserPreferencesFile(Properties defaultPreferences) {
        if (defaultPreferences == null)
            return;

        if (!this.userPreferencesDirectoryExists())
            this.createUserPreferencesDirectory();

        if (!this.userPreferencesFileExists())
            try {
                FileOutputStream outputStream = new FileOutputStream(String.valueOf(this.getUserPreferencesFilePath()));
                defaultPreferences.store(outputStream, null);
            } catch (IOException ignoredForDemoPurposes) {
                ;
            }
    }

    private boolean userPreferencesDirectoryExists() {
        return Files.exists(this.getUserPreferencesDirectoryPath());
    }

    private void createUserPreferencesDirectory() {
        try {
            Files.createDirectories(this.getUserPreferencesDirectoryPath());
        } catch (IOException ignoredForDemoPurposes) {
            ;
        }
    }

    private String getLanguageTagFromProperties() {
        return this.userPreferences.getProperty("language-tag");
    }
}
