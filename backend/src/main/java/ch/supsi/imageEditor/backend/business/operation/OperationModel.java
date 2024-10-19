package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.dataaccess.operation.OperationDataAccess;

public class OperationModel implements OperationModelInterface {
    private static OperationModel instance;
    private final OperationDataAccess operationDataAccess;

    private OperationModel() {
        this.operationDataAccess = OperationDataAccess.getInstance();
    }

    public static OperationModel getInstance() {
        return instance == null ? instance = new OperationModel() : instance;
    }

}
