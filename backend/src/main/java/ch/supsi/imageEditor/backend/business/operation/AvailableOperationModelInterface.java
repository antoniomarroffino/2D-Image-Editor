package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.business.operation.allOperations.Operation;
import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;

import java.util.Map;
import java.util.Set;

public interface AvailableOperationModelInterface {
    Map<String, Operation> getAvailableOperations();
    Set<String> getOperationsTag();
    void checkOperationExists(String operation) throws OperationNotSupportedException;
}
