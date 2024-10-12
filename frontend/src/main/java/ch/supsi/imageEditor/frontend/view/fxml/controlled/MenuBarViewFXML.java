package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.frontend.controller.AppEventHandler;
import ch.supsi.imageEditor.frontend.controller.EventHandler;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.AppModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;

public class MenuBarViewFXML implements ControlledFxView {
    private static MenuBarViewFXML instance = null;
    private static final String PathResourceFXML = "/menubar.fxml";

    private AppEventHandler appEventHandler;
    private AppModel appModel;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu fileMenu;
    @FXML
    private Menu editMenu;
    @FXML
    private Menu helpMenu;

    @FXML
    private MenuItem openMenuItem;
    @FXML
    private Menu openRecentMenu;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem saveAsMenuItem;
    @FXML
    private MenuItem quitMenuItem;
    @FXML
    private Menu languageMenu;
    @FXML
    private MenuItem aboutMenuItem;

    private MenuBarViewFXML() {}

    public static MenuBarViewFXML getInstance() {
        if (instance == null) {
            instance = new MenuBarViewFXML();
            try{
                URL fxmlUrl = OperationViewFXML.class.getResource(PathResourceFXML);
                if(fxmlUrl != null){
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                    fxmlLoader.setController(instance);
                    fxmlLoader.load();
                }
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }

        return instance;
    }
    @Override
    public Node getNode() {
        return this.menuBar;
    }

    @Override
    public void initialize(EventHandler eventHandler, AbstractModel model) {
        this.createBehaviour();
        this.appEventHandler = (AppEventHandler) eventHandler;
        this.appModel = (AppModel) model;
    }

    private void createBehaviour() {
        //About
        this.aboutMenuItem.setOnAction(event -> this.appEventHandler.about());
    }
}
