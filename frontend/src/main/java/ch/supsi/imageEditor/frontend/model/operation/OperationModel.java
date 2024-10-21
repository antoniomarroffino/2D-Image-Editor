package ch.supsi.imageEditor.frontend.model.operation;

import ch.supsi.imageEditor.backend.application.operation.OperationController;
import ch.supsi.imageEditor.backend.application.operation.OperationControllerInterface;
import ch.supsi.imageEditor.backend.application.pipeline.PipelineController;
import ch.supsi.imageEditor.backend.application.pipeline.PipelineControllerInterface;

import java.util.Set;

public class OperationModel implements OperationModelInterface {
    private static OperationModel instance;
    private final OperationControllerInterface operationController;
    private final PipelineControllerInterface pipelineController;
    private final Set<String> operationsTag;

    private OperationModel() {
        this.operationController = OperationController.getInstance();
        this.pipelineController = PipelineController.getInstance();
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
