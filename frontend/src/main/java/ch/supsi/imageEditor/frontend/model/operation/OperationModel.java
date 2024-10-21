package ch.supsi.imageEditor.frontend.model.operation;

import ch.supsi.imageEditor.backend.application.operation.OperationController;
import ch.supsi.imageEditor.backend.application.operation.OperationControllerInterface;

import java.util.Set;

public class OperationModel implements OperationModelInterface {
    private static OperationModel instance;
    private final OperationControllerInterface operationController;
    private final Set<String> operationsTag;

    private OperationModel() {
        this.operationController = OperationController.getInstance();
        this.operationsTag = this.operationController.getOperationsTag();
    }

    public static OperationModel getInstance() {
        return instance == null ? instance = new OperationModel() : instance;
    }

    @Override
    public Set<String> getSupportedOperations() {
        return operationsTag;
    }
}
