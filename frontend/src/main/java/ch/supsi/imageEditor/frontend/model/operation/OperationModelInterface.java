package ch.supsi.imageEditor.frontend.model.operation;

import java.util.Set;

public interface OperationModelInterface {
    Set<String> getSupportedOperations();
    void addOperationToPipeline(String name);
}
