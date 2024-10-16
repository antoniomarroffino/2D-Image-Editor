package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled;

import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

public interface UncontrolledView extends DataView {
    void initialize(AbstractModel model, HandleViewModelInterface handleViewModel);
}
