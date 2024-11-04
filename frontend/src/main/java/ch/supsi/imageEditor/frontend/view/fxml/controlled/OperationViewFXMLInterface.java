package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import java.util.Set;

public interface OperationViewFXMLInterface extends ControlledFxView {
    void createSupportedOperationsButtons(Set<String> supportedOperations);
}
