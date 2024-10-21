package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import java.util.Set;
import ch.supsi.imageEditor.frontend.model.operation.OperationModelInterface;

public interface OperationViewFXMLInterface extends ControlledFxView {
    void createOperationMenuItem(Set<String> supportedOperations);
    void createSupportedOperationsButtons(OperationModelInterface operationModel);
}
