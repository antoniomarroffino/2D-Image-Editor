package ch.supsi.imageEditor.frontend.controller;

import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.AppModel;
import ch.supsi.imageEditor.frontend.view.popup.about.AboutViewInterface;
import ch.supsi.imageEditor.frontend.view.popup.about.AboutViewPopUp;

public class AppController implements AppControllerInterface {
    private static AppController instance = null;
    private final AppModel appModel;

    private final AboutViewInterface aboutView;

    private AppController() {
        this.appModel = AppModel.getInstance();
        this.aboutView = AboutViewPopUp.getInstance();
    }

    public static AppController getInstance() {
        return instance == null ? instance = new AppController() : instance;
    }

    @Override
    public void about(Component node) {
        this.aboutView.showAboutInformation();
    }
}
