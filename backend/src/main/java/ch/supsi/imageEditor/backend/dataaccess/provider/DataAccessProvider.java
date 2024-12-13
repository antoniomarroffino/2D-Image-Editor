package ch.supsi.imageEditor.backend.dataaccess.provider;

public class DataAccessProvider {
    private static DataAccessProvider instance;

    private final String defaultPreferencesPath = "/default-user-preferences.properties";
    private final String userHomeDirectory = System.getProperty("user.home");
    private final String preferencesDirectory = ".userpreferences";
    private final String preferencesFile = "preferences.properties";

    private final String operationsFile = "/operations.properties";
    private final String operationConcreteOperationPath = "/operation-concreteOperation.properties";
    private final String formatReaderPropertiesPath = "/format-reader.properties";
    private final String formatExporterPropertiesPath = "/format-exporter.properties";
    private final String recentFiles = "recentFiles.txt";

    protected DataAccessProvider() {

    }


    public static DataAccessProvider getInstance() {
        return instance == null ? instance = new DataAccessProvider() : instance;
    }

    public String getDefaultPreferencesPath() {
        return defaultPreferencesPath;
    }

    public String getUserHomeDirectory() {
        return userHomeDirectory;
    }

    public String getPreferencesDirectory() {
        return preferencesDirectory;
    }

    public String getPreferencesFile() {
        return preferencesFile;
    }

    public String getOperationsFile() {
        return operationsFile;
    }

    public String getOperationConcreteOperationPath() {
        return operationConcreteOperationPath;
    }

    public String getFormatReaderPropertiesPath() {
        return formatReaderPropertiesPath;
    }

    public String getFormatExporterPropertiesPath() {
        return formatExporterPropertiesPath;
    }

    public String getRecentFiles() {
        return recentFiles;
    }

}
