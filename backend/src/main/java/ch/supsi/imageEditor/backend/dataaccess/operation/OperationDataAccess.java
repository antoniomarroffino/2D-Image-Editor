package ch.supsi.imageEditor.backend.dataaccess.operation;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OperationDataAccess implements OperationDataAccessInterface {
    private static OperationDataAccess instance;
    private static final String operationsFile = "operations.properties";

    public static OperationDataAccess getInstance() {
        return instance == null ? instance = new OperationDataAccess() : instance;
    }

    @Override
    public Set<String> getOperationsTag() {
        return new HashSet<>(Arrays.asList("rotazione", "traslazione", "capovolgimento"));
    }
}
