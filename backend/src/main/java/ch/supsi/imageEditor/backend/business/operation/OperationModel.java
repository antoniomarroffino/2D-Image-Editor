package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.dataaccess.operation.OperationDataAccess;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class OperationModel implements OperationModelInterface {
    private static OperationModel instance;
    private final OperationDataAccess operationDataAccess;
    private final Set<String> supportedOperations;

    private OperationModel() {
        this.operationDataAccess = OperationDataAccess.getInstance();
        this.supportedOperations = this.operationDataAccess.getOperationsTag();
    }

    public static OperationModel getInstance() {
        return instance == null ? instance = new OperationModel() : instance;
    }

    private Set<String> orderOperations(Set<String> operations) {
        return operations.stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void checkOperationExists(String operation) {
        if(!this.supportedOperations.contains(operation)) {
            //TODO: lanciare eccezione
        }
    }

    @Override
    public Set<String> getOperationsTag() {
        return this.orderOperations(this.supportedOperations);
    }
}
