package ch.supsi.imageEditor.backend.dataaccess.images;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.PNM.PbmImage;
import ch.supsi.imageEditor.backend.business.images.PNM.PgmImage;
import ch.supsi.imageEditor.backend.business.images.PNM.PpmImage;
import ch.supsi.imageEditor.backend.business.images.export.ExportToPgmStrategy;
import ch.supsi.imageEditor.backend.business.images.export.ExportToPpmStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImageDataAccess implements ImageDataAccessInterface {
    private static final String formatReaderPropertiesPath = "/format-reader.properties";
    private static final String formatExporterPropertiesPath = "/format-exporter.properties";
    private static final String userHomeDirectory = System.getProperty("user.home");
    private static final String preferencesDirectory = ".userpreferences";
    private static final String recentFiles = "recentFiles.txt";
    private final Path filePath;
    private static ImageDataAccess instance = null;
    private final Properties formatReaderProperties;
    private final Properties formatExporterProperties;

    private ImageDataAccess() {
        this.formatReaderProperties = this.loadFormatReaderProperties();
        this.formatExporterProperties = this.loadFormatExporterProperties();
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

    private Properties loadFormatExporterProperties() {
        Properties defaultPreferences = new Properties();
        try {
            InputStream defaultPreferencesStream = this.getClass().getResourceAsStream(formatExporterPropertiesPath);
            defaultPreferences.load(defaultPreferencesStream);
        } catch (IOException ignored) {
            ;
        }
        return defaultPreferences;
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
            if (image instanceof PbmImage)
                if (getFileExtension(file.getPath()).equals("pbm"))
                    writer.write(image.toString());
            if (image instanceof PgmImage)
                if (getFileExtension(file.getPath()).equals("pbm"))
                    writer.write(image.export(new ExportToPgmStrategy()).toString());
                else
                    writer.write(image.toString());
            if (image instanceof PpmImage)
                if (getFileExtension(file.getPath()).equals("pbm")) {
                    image = image.export(new ExportToPpmStrategy());
                    writer.write(image.export(new ExportToPgmStrategy()).toString());
                } else if (getFileExtension(file.getPath()).equals("pgm"))
                    writer.write(image.export(new ExportToPpmStrategy()).toString());
                else
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
