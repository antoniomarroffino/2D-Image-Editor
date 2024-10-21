package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.controller.observer.HandleServiceInterface;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PipelineViewFXML implements ControlledFxView {
    private static final String PathResourceFXML = "/pipeline.fxml";
    private static PipelineViewFXML instance = null;
    private HandleServiceInterface handleService;

    @FXML
    private Pane pipelinePane;

    @FXML
    private ScrollPane pipelineScrollPane;

    @FXML
    private VBox pipelineVBox;

    private PipelineViewFXML() {
    }

    public static PipelineViewFXML getInstance(ResourceBundle resourceBundle) {
        if (instance == null) {
            instance = new PipelineViewFXML();
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
        return this.pipelinePane;
    }

    @Override
    public void initialize(HandleServiceInterface handleService, AbstractModel model) {
        this.handleService = handleService;
    }

    @Override
    public void update(EventType eventType) {

    }
}
