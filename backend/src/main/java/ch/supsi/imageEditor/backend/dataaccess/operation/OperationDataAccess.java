package ch.supsi.imageEditor.backend.dataaccess.operation;

import ch.supsi.imageEditor.backend.dataaccess.provider.DataAccessProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class OperationDataAccess implements OperationDataAccessInterface {
    protected static OperationDataAccess instance;

    private final String operationsFile;
    private final String operationConcreteOperationPath;
    private final Properties supportedOperationsProperties;
    private final Properties operationsProperties;

    protected OperationDataAccess() {
        DataAccessProvider dataAccessProvider = DataAccessProvider.getInstance();
        this.operationsFile = dataAccessProvider.getOperationsFile();
        this.operationConcreteOperationPath = dataAccessProvider.getOperationConcreteOperationPath();
        this.supportedOperationsProperties = this.getSupportedOperationsProperties();
        this.operationsProperties = this.getOperationsProperties();
    }

    public static OperationDataAccess getInstance() {
        if (instance == null) {
            instance = new OperationDataAccess();
        }
        return instance;
    }


    private Properties getSupportedOperationsProperties() {
        Properties defaultPreferences = new Properties();
        try {
            InputStream defaultPreferencesStream = this.getClass().getResourceAsStream(operationsFile);
            defaultPreferences.load(defaultPreferencesStream);
        } catch (IOException ignored) {
            ;
        }
        return defaultPreferences;
    }

    private Properties getOperationsProperties() {
        Properties defaultPreferences = new Properties();
        try {
            InputStream defaultPreferencesStream = this.getClass().getResourceAsStream(operationConcreteOperationPath);
            defaultPreferences.load(defaultPreferencesStream);
        } catch (IOException ignored) {
            ;
        }
        return defaultPreferences;
    }

    @Override
    public Properties getOperationProperties() {
        return this.operationsProperties;
    }

    @Override
    public Set<String> getOperationsTag() {
        return supportedOperationsProperties.stringPropertyNames();
    }
}
