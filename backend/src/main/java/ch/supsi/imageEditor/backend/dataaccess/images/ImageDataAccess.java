package ch.supsi.imageEditor.backend.dataaccess.images;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ImageDataAccess implements ImageDataAccessInterface {
    private static final String formatReaderPropertiesPath = "/format-reader.properties";
    private static final String userHomeDirectory = System.getProperty("user.home");
    private static final String preferencesDirectory = ".userpreferences";
    private static final String recentFiles = "recentFiles.txt";
    private final Path filePath;
    private static ImageDataAccess instance = null;
    private final Properties formatReaderProperties;

    private ImageDataAccess() {
        this.formatReaderProperties = this.loadFormatReaderProperties();
        this.filePath = loadFilePath();
    }

    public static ImageDataAccess getInstance() {
        return instance == null ? instance = new ImageDataAccess() : instance;
    }

    private Path loadFilePath() {
        Path preferencesPath = Paths.get(userHomeDirectory, preferencesDirectory);
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

    public Properties getFormatReaderProperties() {
        return this.formatReaderProperties;
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
            for (String recentFile : recentFiles){
                writer.write(recentFile);
                writer.newLine();
            }
        } catch (IOException ignored) {
            ;
        }
    }
}
