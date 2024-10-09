package ch.supsi.ImageEditor.frontend.view;

import ch.supsi.ImageEditor.frontend.model.AbstractModel;

public interface UncontrolledView extends DataView {
    void initialize(AbstractModel model);
}
