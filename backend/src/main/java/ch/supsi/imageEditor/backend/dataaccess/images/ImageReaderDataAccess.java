package ch.supsi.imageEditor.backend.dataaccess.images;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ImageReaderDataAccess implements ImageReaderDataAccessInterface {
    private static final String formatReaderPropertiesPath = "/format-reader.properties";
    private static ImageReaderDataAccess instance = null;
    private final Properties formatReaderProperties;

    private ImageReaderDataAccess() {
        this.formatReaderProperties = this.loadFormatReaderProperties();
    }

    public static ImageReaderDataAccess getInstance() {
        return instance == null ? instance = new ImageReaderDataAccess() : instance;
    }

    private Properties loadFormatReaderProperties() {
        Properties defaultPreferences = new Properties();
        try {
            InputStream defaultPreferencesStream = this.getClass().getResourceAsStream(formatReaderPropertiesPath);
            defaultPreferences.load(defaultPreferencesStream);
        } catch (IOException ignored) {
            ;
        }
        return defaultPreferences;
    }

    public Properties getFormatReaderProperties() {
        return this.formatReaderProperties;
    }
}
