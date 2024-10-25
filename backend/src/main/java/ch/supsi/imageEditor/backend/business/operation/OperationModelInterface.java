package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;

import java.util.Set;

public interface OperationModelInterface {
    Set<String> getOperationsTag();
    void checkOperationExists(String operation) throws OperationNotSupportedException;
}
