package ch.supsi.ImageEditor.frontend.view;

import ch.supsi.ImageEditor.frontend.controller.EventHandler;
import ch.supsi.ImageEditor.frontend.model.AbstractModel;

public interface ControlledView extends DataView {
    void initialize(EventHandler eventHandler, AbstractModel model);
}
