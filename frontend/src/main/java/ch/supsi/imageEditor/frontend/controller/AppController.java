package ch.supsi.imageEditor.frontend.controller;

import ch.supsi.imageEditor.frontend.model.AppModel;

public class AppController implements AppEventHandler {
    private static AppController instance = null;
    private final AppModel appModel;

    private AppController() {
        this.appModel = AppModel.getInstance();
    }

    public static AppController getInstance() {
        return instance == null ? instance = new AppController() : instance;
    }

    @Override
    public void about() {

    }
}
