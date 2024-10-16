package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.frontend.controller.observer.HandleServiceInterface;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

public interface ControlledView extends DataView {
    void initialize(HandleServiceInterface handleService, AbstractModel model, HandleViewModelInterface handleViewModel);
}
