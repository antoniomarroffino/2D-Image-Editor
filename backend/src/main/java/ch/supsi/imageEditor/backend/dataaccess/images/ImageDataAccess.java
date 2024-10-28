package ch.supsi.imageEditor.backend.dataaccess.images;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

import java.io.*;
import java.util.Properties;

public class ImageDataAccess implements ImageDataAccessInterface {
    private static final String formatReaderPropertiesPath = "/format-reader.properties";
    private static ImageDataAccess instance = null;
    private final Properties formatReaderProperties;

    private ImageDataAccess() {
        this.formatReaderProperties = this.loadFormatReaderProperties();
    }

    public static ImageDataAccess getInstance() {
        return instance == null ? instance = new ImageDataAccess() : instance;
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

    @Override
    public void writeImage(AbstractImage image, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(image.getFormat() + System.lineSeparator());
            writer.write("# Image edited by 2D Image Editor" + System.lineSeparator());
            writer.write(image.getWidth() + " " + image.getHeight() + System.lineSeparator());
            Pixel[][] pixel = image.getPixel();
            for (int i = 0; i < image.getHeight(); i++)
                for (int j = 0; j < image.getWidth(); j++)
                        writer.write(pixel[i][j] + " ");
        } catch (IOException ignored) {
           ;
        }
    }
}
