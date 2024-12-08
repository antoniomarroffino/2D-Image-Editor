package ch.supsi.imageEditor.frontend.view.fxml.controlled.operation;

import ch.supsi.imageEditor.frontend.view.fxml.controlled.ControlledFxView;

import java.util.Set;

public interface OperationViewFXMLInterface extends ControlledFxView {
    void createSupportedOperationsButtons(Set<String> supportedOperations);
}
