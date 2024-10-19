package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.frontend.model.operation.OperationModelInterface;

public interface OperationViewFXMLInterface extends ControlledFxView {
    void createSupportedOperationsButtons(OperationModelInterface operationModel);
}
