package ch.supsi.imageEditor.frontend.view;

import ch.supsi.imageEditor.frontend.controller.EventHandler;
import ch.supsi.imageEditor.frontend.model.AbstractModel;

public interface ControlledView extends DataView {
    void initialize(EventHandler eventHandler, AbstractModel model);
}
