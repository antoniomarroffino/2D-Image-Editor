package ch.supsi.imageEditor.frontend.controller.language;

import ch.supsi.imageEditor.frontend.controller.EventHandler;
import javafx.scene.control.MenuItem;

public interface LanguageControllerInterface extends EventHandler {
    void changeLanguage(MenuItem node);
}
