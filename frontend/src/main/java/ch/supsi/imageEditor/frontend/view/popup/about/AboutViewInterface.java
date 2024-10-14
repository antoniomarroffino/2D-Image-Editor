package ch.supsi.imageEditor.frontend.view.popup.about;

import ch.supsi.imageEditor.frontend.model.about.AboutModelInterface;

public interface AboutViewInterface {
    void showAboutInformation();

    void initialize(AboutModelInterface aboutModel);
}
