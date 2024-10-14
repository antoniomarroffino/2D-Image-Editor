package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.frontend.model.language.LanguageModelInterface;

public interface MenuBarViewFXMLInterface extends ControlledFxView {
    void createSupportedLanguagesMenuItem(LanguageModelInterface languageModel);
}
