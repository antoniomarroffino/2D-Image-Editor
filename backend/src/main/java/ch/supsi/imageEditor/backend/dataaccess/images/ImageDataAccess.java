package ch.supsi.imageEditor.backend.dataaccess.images;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImageDataAccess implements ImageDataAccessInterface {
    protected static ImageDataAccess instance = null;

    private static final String formatReaderPropertiesPath = "/format-reader.properties";
    private static final String formatExporterPropertiesPath = "/format-exporter.properties";
    private static final String userHomeDirectory = System.getProperty("user.home");
    private static final String preferencesDirectory = ".userpreferences";
    private static final String recentFiles = "recentFiles.txt";
    private final Path filePath;
    private final Properties formatReaderProperties;
    private final Properties formatExporterProperties;
    private final Path preferencesPath;

    private ImageDataAccess() {
        this.formatReaderProperties = this.loadProperties(formatReaderPropertiesPath);
        this.formatExporterProperties = this.loadProperties(formatExporterPropertiesPath);
        this.preferencesPath = Paths.get(userHomeDirectory, preferencesDirectory);
        this.filePath = loadFilePath(preferencesPath);
    }

    public static ImageDataAccess getInstance() {
        return instance == null ? instance = new ImageDataAccess() : instance;
    }


    private Path loadFilePath(Path preferencesPath) {
        try {
            if (!Files.exists(preferencesPath))
                Files.createDirectories(preferencesPath);
        } catch (IOException ignored) {
            ;
        }
        return preferencesPath.resolve(recentFiles);
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

    private Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(fileName);
            properties.load(inputStream);
        } catch (IOException ignored) {
            ;
        }
        return properties;
    }

    @Override
    public Properties getFormatReaderProperties() {
        return this.formatReaderProperties;
    }

    @Override
    public Properties getFormatExporterProperties() {
        return this.formatExporterProperties;
    }

    protected String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filePath.length() - 1)
            return "";
        return filePath.substring(lastDotIndex + 1);
    }

    @Override
    public void writeImage(AbstractImage image, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(image.toString());
        } catch (IOException ignored) {
            ;
        }
    }

    @Override
    public List<String> getRecentFiles() {
        List<String> recentFilesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recentFilesList.add(line);
            }
        } catch (IOException ignored) {
            ;
        }
        return recentFilesList;
    }

    @Override
    public void persistRecentFile(List<String> recentFiles) {
        System.out.println(recentFiles);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath.toFile()))) {
            for (String recentFile : recentFiles) {
                writer.write(recentFile);
                writer.newLine();
            }
        } catch (IOException ignored) {
            ;
        }
    }
}
