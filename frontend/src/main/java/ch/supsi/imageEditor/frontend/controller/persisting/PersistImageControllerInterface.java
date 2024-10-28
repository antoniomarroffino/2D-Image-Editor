package ch.supsi.imageEditor.frontend.controller.persisting;

import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

import java.util.List;

public interface PersistImageControllerInterface {
    void initialize(List<DataView> views);

    void openImage(Component component);

    void saveImage(Component component);

    void saveImageAs(Component component);
}
