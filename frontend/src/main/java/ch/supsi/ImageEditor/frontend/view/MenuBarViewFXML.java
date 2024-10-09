package ch.supsi.ImageEditor.frontend.view;

import ch.supsi.ImageEditor.frontend.controller.EventHandler;
import ch.supsi.ImageEditor.frontend.model.AbstractModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;

import java.io.IOException;
import java.net.URL;

public class MenuBarViewFXML implements ControlledFxView {
    private static MenuBarViewFXML instance = null;
    private static final String PathResourceFXML = "/menubar.fxml";

    @FXML
    private MenuBar menuBar;

    private MenuBarViewFXML() {}

    public static MenuBarViewFXML getInstance() {
        if (instance == null)
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
        return instance;
    }
    @Override
    public Node getNode() {
        return this.menuBar;
    }

    @Override
    public void initialize(EventHandler eventHandler, AbstractModel model) {

    }
}
