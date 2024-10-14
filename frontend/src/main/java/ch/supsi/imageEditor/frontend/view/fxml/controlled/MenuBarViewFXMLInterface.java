package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.frontend.controller.observer.HandleServiceInterface;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.language.LanguageModelInterface;
import javafx.scene.Node;

public interface MenuBarViewFXMLInterface {
    void initialize(HandleServiceInterface handleService, AbstractModel model);

    Node getNode();

    void createSupportedLanguagesMenuItem(LanguageModelInterface languageModel);
}
