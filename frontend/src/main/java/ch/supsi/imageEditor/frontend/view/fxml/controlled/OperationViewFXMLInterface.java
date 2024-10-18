package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import java.util.Set;

public interface OperationViewFXMLInterface extends ControlledFxView {
    void createOperationMenuItem(Set<String> supportedOperations);
}
