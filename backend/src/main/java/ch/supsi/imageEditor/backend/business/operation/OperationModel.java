package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.dataaccess.operation.OperationDataAccess;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class OperationModel implements OperationModelInterface {
    private static OperationModel instance;
    private final OperationDataAccess operationDataAccess;

    private OperationModel() {
        this.operationDataAccess = OperationDataAccess.getInstance();
    }

    public static OperationModel getInstance() {
        return instance == null ? instance = new OperationModel() : instance;
    }

    private Set<String> orderOperations(Set<String> operations) {
        return operations.stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<String> getOperationsTag() {
        return this.orderOperations(this.operationDataAccess.getOperationsTag());
    }
}
