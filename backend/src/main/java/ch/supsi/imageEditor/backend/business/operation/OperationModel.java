package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.dataaccess.operation.OperationDataAccess;

import java.util.Set;

public class OperationModel implements OperationModelInterface {
    private static OperationModel instance;
    private final OperationDataAccess operationDataAccess;

    private OperationModel() {
        this.operationDataAccess = OperationDataAccess.getInstance();
    }

    public static OperationModel getInstance() {
        return instance == null ? instance = new OperationModel() : instance;
    }

    @Override
    public Set<String> getOperationsTag() {
        return this.operationDataAccess.getOperationsTag();
    }
}
