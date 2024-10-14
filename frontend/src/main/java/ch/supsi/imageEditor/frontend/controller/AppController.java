package ch.supsi.imageEditor.frontend.controller;

import ch.supsi.imageEditor.frontend.controller.observer.EventOnApplication;
import ch.supsi.imageEditor.frontend.controller.observer.HandleService;
import ch.supsi.imageEditor.frontend.controller.observer.HandleServiceInterface;
import ch.supsi.imageEditor.frontend.model.AppModel;
import ch.supsi.imageEditor.frontend.view.popup.about.AboutViewInterface;
import ch.supsi.imageEditor.frontend.view.popup.about.AboutViewPopUp;
import javafx.scene.control.MenuItem;

public class AppController implements EventHandler{
    private static AppController instance = null;
    private final AppModel appModel;

    private final AboutViewInterface aboutView;

    private final HandleServiceInterface handleService;

    private AppController() {
        this.appModel = AppModel.getInstance();
        this.aboutView = AboutViewPopUp.getInstance();
        this.handleService = HandleService.getInstance();
        this.handleService.subscribe(EventOnApplication.ABOUT, this::about);
    }

    public static AppController getInstance() {
        return instance == null ? instance = new AppController() : instance;
    }

    public void about(MenuItem node) {
        this.aboutView.showAboutInformation();
    }
}
