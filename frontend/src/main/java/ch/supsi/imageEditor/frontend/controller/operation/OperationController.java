package ch.supsi.imageEditor.frontend.controller.operation;

import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.operation.OperationModel;
import ch.supsi.imageEditor.frontend.model.operation.OperationModelInterface;


public class OperationController implements OperationControllerInterface {
    private static OperationController instance = null;
    private final OperationModelInterface operationModel;


    private OperationController() {
        this.operationModel = OperationModel.getInstance();
    }

    public static OperationController getInstance() {
        return instance == null ? instance = new OperationController() : instance;
    }

    @Override
    public void addOperationToPipeline(Component node) {
        System.out.println("CIAO - " + node.getId()); //TODO: gestire l'evento
    }
}
