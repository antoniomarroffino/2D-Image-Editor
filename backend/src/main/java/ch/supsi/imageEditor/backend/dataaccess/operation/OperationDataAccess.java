package ch.supsi.imageEditor.backend.dataaccess.operation;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class OperationDataAccess implements OperationDataAccessInterface {
    private static final String operationsFile = "/operations.properties";
    private static final String operationConcreteOperationPath = "/operation-concreteOperation.properties";
    protected static OperationDataAccess instance;
    private final Properties supportedOperationsProperties;
    private final Properties operationsProperties;

    protected OperationDataAccess() {
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
