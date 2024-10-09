package ch.supsi.ImageEditor.frontend.view;

import ch.supsi.ImageEditor.frontend.model.AbstractModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class CurrentInfoViewFXML implements UncontrolledFxView {
    private static CurrentInfoViewFXML instance = null;
    private static final String PathResourceFXML = "/currentinfo.fxml";

    @FXML
    private Pane currentInfoPane;

    private CurrentInfoViewFXML() {}

    public static CurrentInfoViewFXML getInstance() {
        if (instance == null)
            instance = new CurrentInfoViewFXML();

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
        return this.currentInfoPane;
    }

    @Override
    public void initialize(AbstractModel model) {

    }
}
