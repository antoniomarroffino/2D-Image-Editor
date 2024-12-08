package ch.supsi.imageEditor.frontend.view.fxml.controlled.menubar;

import ch.supsi.imageEditor.frontend.view.fxml.controlled.ControlledFxView;

import java.util.List;
import java.util.Set;

public interface MenuBarViewFXMLInterface extends ControlledFxView {
    void createSupportedLanguagesMenuItem(Set<String> supportedLanguages);

    void createOpenRecentMenuItem(List<String> recentFiles);
}
