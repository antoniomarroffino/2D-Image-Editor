package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.frontend.controller.EventHandler;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

public class PipelineViewFXML implements ControlledFxView {
    private static PipelineViewFXML instance = null;
    private static final String PathResourceFXML = "/pipeline.fxml";

    @FXML
    private Pane pipelinePane;

    @FXML
    private ScrollPane pipelineScrollPane;

    @FXML
    private VBox pipelineVBox;

    private PipelineViewFXML() {}

    public static PipelineViewFXML getInstance() {
        if (instance == null) {
            instance = new PipelineViewFXML();
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
        return this.pipelinePane;
    }

    @Override
    public void initialize(EventHandler eventHandler, AbstractModel model) {

    }
}
