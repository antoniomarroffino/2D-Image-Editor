package ch.supsi.imageEditor.frontend.model.operation;

import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;

import java.util.Set;

public interface OperationModelInterface {
    Set<String> getSupportedOperations();

    void addOperationToPipeline(String name) throws OperationNotSupportedException;
}
