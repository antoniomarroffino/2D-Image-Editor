package ch.supsi.ImageEditor.frontend.controller;

import ch.supsi.ImageEditor.frontend.model.AppModel;

public class AppController implements AppEventHandler{
    private static AppController instance = null;
    private final AppModel appModel;

    private AppController() {
        this.appModel = AppModel.getInstance();
    }

    public static AppController getInstance() {
        return instance == null ? instance = new AppController() : instance;
    }
}
