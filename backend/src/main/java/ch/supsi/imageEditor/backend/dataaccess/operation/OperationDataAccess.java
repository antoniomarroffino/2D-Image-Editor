package ch.supsi.imageEditor.backend.dataaccess.operation;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class OperationDataAccess implements OperationDataAccessInterface {
    private static OperationDataAccess instance;
    private static final String operationsFile = "operations.properties";
    private Properties operationsProperties;

    private OperationDataAccess() {
        operationsProperties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(operationsFile)) {
            if (input == null) {
                System.err.println("Sorry, unable to find " + operationsFile);
                return;
            }
            operationsProperties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace(); //TODO: gestire l'errore
        }
    }

    public static OperationDataAccess getInstance() {
        if (instance == null) {
            instance = new OperationDataAccess();
        }
        return instance;
    }

    @Override
    public Set<String> getOperationsTag() {
        return operationsProperties.stringPropertyNames();
    }
}
