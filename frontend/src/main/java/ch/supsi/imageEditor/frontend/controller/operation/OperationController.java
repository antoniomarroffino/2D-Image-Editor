package ch.supsi.imageEditor.frontend.controller.operation;

import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.operation.OperationModel;
import ch.supsi.imageEditor.frontend.model.operation.OperationModelInterface;
import ch.supsi.imageEditor.frontend.view.popup.error.ErrorViewInterface;
import ch.supsi.imageEditor.frontend.view.popup.error.ErrorViewPopUp;


public class OperationController implements OperationControllerInterface {
    private static OperationController instance = null;
    private final OperationModelInterface operationModel;
    private final ErrorViewInterface errorView;


    private OperationController() {
        this.operationModel = OperationModel.getInstance();
        this.errorView = ErrorViewPopUp.getInstance();
    }

    public static OperationController getInstance() {
        return instance == null ? instance = new OperationController() : instance;
    }

    @Override
    public void addOperationToPipeline(Component node) {
        try {
            this.operationModel.addOperationToPipeline(node.getId());
        } catch (OperationNotSupportedException e) {
            this.errorView.showPopUpError(e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
