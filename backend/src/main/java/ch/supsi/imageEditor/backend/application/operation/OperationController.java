package ch.supsi.imageEditor.backend.application.operation;

import ch.supsi.imageEditor.backend.business.operation.OperationModel;

import java.util.Set;

public class OperationController implements OperationControllerInterface {
    protected static OperationController instance = null;

    private final OperationModel operationModel;
    //private final NotificationServiceInterface notificationService;

    protected OperationController() {
        this.operationModel = OperationModel.getInstance();
        //this.notificationService = NotificationService.getInstance();
    }

    public static OperationController getInstance() {
        return instance == null ? instance = new OperationController() : instance;
    }

    @Override
    public Set<String> getOperationsTag() {
        return this.operationModel.getOperationsTag();
    }
}
