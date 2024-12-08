package ch.supsi.imageEditor.backend.application.operation;

import ch.supsi.imageEditor.backend.business.operation.AvailableOperationModel;
import ch.supsi.imageEditor.backend.business.operation.AvailableOperationModelInterface;

import java.util.Set;

public class OperationController implements OperationControllerInterface {
    protected static OperationController instance = null;

    private final AvailableOperationModelInterface availableOperationModel;

    protected OperationController() {
        this.availableOperationModel = AvailableOperationModel.getInstance();
    }

    public static OperationController getInstance() {
        return instance == null ? instance = new OperationController() : instance;
    }


    @Override
    public Set<String> getOperationsTag() {
        return this.availableOperationModel.getOperationsTag();
    }
}
