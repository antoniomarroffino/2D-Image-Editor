package ch.supsi.imageEditor.frontend.view;

import ch.supsi.imageEditor.frontend.model.AbstractModel;

public interface UncontrolledView extends DataView {
    void initialize(AbstractModel model);
}
