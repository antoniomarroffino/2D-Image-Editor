package ch.supsi.imageEditor.frontend.controller.language;

import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

import java.util.List;

public interface LanguageControllerInterface {
    void changeLanguage(Component node);

    void initialize(List<DataView> views);
}
