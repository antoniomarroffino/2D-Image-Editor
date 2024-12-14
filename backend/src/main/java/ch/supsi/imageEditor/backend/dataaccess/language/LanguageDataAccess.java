package ch.supsi.imageEditor.backend.dataaccess.language;

import ch.supsi.imageEditor.backend.dataaccess.provider.DataAccessProvider;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class LanguageDataAccess implements LanguageDataAccessInterface {
    protected static LanguageDataAccess instance;

    private final String defaultPreferencesPath;
    private final String userHomeDirectory;
    private final String preferencesDirectory;
    private final String preferencesFile;
    private Properties userPreferences;

    protected LanguageDataAccess() {
        DataAccessProvider dataAccessProvider = DataAccessProvider.getInstance();
        this.userPreferences = null;
        this.defaultPreferencesPath = dataAccessProvider.getDefaultPreferencesPath();
        this.userHomeDirectory = dataAccessProvider.getUserHomeDirectory();
        this.preferencesDirectory = dataAccessProvider.getPreferencesDirectory();
        this.preferencesFile = dataAccessProvider.getPreferencesFile();
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
        this.createUserPreferencesFile(this.userPreferences);

        return this.getLanguageTagFromProperties();
    }

    @Override
    public void changeLanguage(String languageTag) {
        String content = "language-tag=" + languageTag;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(this.getUserPreferencesFilePath())))) {
            writer.write(content);
        } catch (IOException ignored) {
            ;
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
        try (FileInputStream inputStream = new FileInputStream(String.valueOf(path))) {
            preferences.load(inputStream);
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


        if (!this.userPreferencesFileExists()) {
            Path preferencesPath = this.getUserPreferencesFilePath();
            try (FileOutputStream outputStream = new FileOutputStream(preferencesPath.toFile())) {
                defaultPreferences.store(outputStream, null);
            } catch (IOException ignoredForDemoPurposes) {
                ;
            }
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
