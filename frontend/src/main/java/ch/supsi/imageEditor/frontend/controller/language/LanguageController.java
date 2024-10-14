package ch.supsi.imageEditor.frontend.controller.language;

import ch.supsi.imageEditor.frontend.controller.EventHandler;
import ch.supsi.imageEditor.frontend.controller.observer.EventOnApplication;
import ch.supsi.imageEditor.frontend.controller.observer.HandleService;
import ch.supsi.imageEditor.frontend.controller.observer.HandleServiceInterface;
import ch.supsi.imageEditor.frontend.model.language.LanguageModel;
import ch.supsi.imageEditor.frontend.model.language.LanguageModelInterface;
import javafx.scene.control.MenuItem;

public class LanguageController implements EventHandler {
    private static LanguageController instance = null;
    private final LanguageModelInterface languageModel;
    private final HandleServiceInterface handleService;

    private LanguageController() {
        this.languageModel = LanguageModel.getInstance();
        this.handleService = HandleService.getInstance();
        this.handleService.subscribe(EventOnApplication.CHANGE_LANGUAGE, this::changeLanguage);
    }

    public static LanguageController getInstance() {
        return instance == null ? instance = new LanguageController() : instance;
    }

    public void changeLanguage(MenuItem node) {
        System.out.println(node.getId());
    }
}
