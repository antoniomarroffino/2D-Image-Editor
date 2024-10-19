package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.OperationViewFXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CurrentInfoViewFXML implements UncontrolledFxView {
    private static CurrentInfoViewFXML instance = null;
    private static final String PathResourceFXML = "/currentinfo.fxml";

    @FXML
    private Pane currentInfoPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label formatLabel;

    @FXML
    private Label dimensionLabel;

    @FXML
    private Label modificationDateLabel;

    private CurrentInfoViewFXML() {
    }

    public static CurrentInfoViewFXML getInstance(ResourceBundle resourceBundle) {
        if (instance == null) {
            instance = new CurrentInfoViewFXML();
            try {
                URL fxmlUrl = OperationViewFXML.class.getResource(PathResourceFXML);
                if (fxmlUrl != null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl, resourceBundle);
                    fxmlLoader.setController(instance);
                    fxmlLoader.load();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return instance;
    }

    @Override
    public Node getNode() {
        return this.currentInfoPane;
    }

    @Override
    public void initialize(AbstractModel model, HandleViewModelInterface handleViewModel) {

    }

    @Override
    public void update(EventType eventType) {

    }
}
