package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.frontend.controller.EventHandler;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

public interface ControlledView extends DataView {
    void initialize(EventHandler eventHandler, AbstractModel model);
}
