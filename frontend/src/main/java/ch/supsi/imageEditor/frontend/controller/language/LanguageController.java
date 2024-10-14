package ch.supsi.imageEditor.frontend.controller.language;

import ch.supsi.imageEditor.frontend.model.language.LanguageModel;
import ch.supsi.imageEditor.frontend.model.language.LanguageModelInterface;
import javafx.scene.control.MenuItem;

public class LanguageController implements LanguageControllerInterface {
    private static LanguageController instance = null;
    private final LanguageModelInterface languageModel;

    private LanguageController() {
        this.languageModel = LanguageModel.getInstance();
    }

    public static LanguageController getInstance() {
        return instance == null ? instance = new LanguageController() : instance;
    }

    @Override
    public void changeLanguage(MenuItem node) {
        System.out.println(node.getId());
    }
}
