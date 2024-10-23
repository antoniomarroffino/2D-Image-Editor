package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.adapter.ButtonAdapter;
import ch.supsi.imageEditor.frontend.controller.observer.EventOnApplication;
import ch.supsi.imageEditor.frontend.controller.observer.HandleServiceInterface;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.pipeline.PipelineModel;
import ch.supsi.imageEditor.frontend.model.pipeline.PipelineModelInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PipelineViewFXML implements ControlledFxView {
    private static final String PathResourceFXML = "/pipeline.fxml";
    private static PipelineViewFXML instance = null;
    private PipelineModelInterface pipelineModel;
    private HandleServiceInterface handleService;
    private final Map<EventType, Runnable> onEventDoActionMap;
    private static ResourceBundle bundle;

    @FXML
    private Pane pipelinePane;

    @FXML
    private ScrollPane pipelineScrollPane;

    @FXML
    private VBox pipelineVBox;

    @FXML
    private Button deleteButton;

    @FXML
    private Button playButton;

    private PipelineViewFXML() {
        this.onEventDoActionMap = new HashMap<>();
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
                    bundle = resourceBundle;
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
        this.pipelineModel = (PipelineModel) model;
        fillMap();
        this.playButton.setOnAction(event -> this.handleService.notify(EventOnApplication.RUN_PIPELINE, new ButtonAdapter((Button) event.getSource())));
        this.deleteButton.setOnAction(event -> this.handleService.notify(EventOnApplication.DELETE_PIPELINE, new ButtonAdapter((Button) event.getSource())));
    }

    private void fillMap() {
        this.onEventDoActionMap.put(EventType.ADDED_OPERATION, this::addOperationToPipeline);
        this.onEventDoActionMap.put(EventType.CLEAR_PIPELINE, this::clearPipeline);
    }

    private void addOperationToPipeline() {
        String operation = this.pipelineModel.getAddedOperation();
        if (operation != null){
            this.enableButtons();
            this.pipelineVBox.getChildren().add(this.createLabel(operation));
        }

    }

    private void clearPipeline() {
        this.pipelineVBox.getChildren().clear();
        this.disableButtons();
    }

    private void enableButtons() {
        this.playButton.setDisable(false);
        this.deleteButton.setDisable(false);
    }

    private void disableButtons() {
        this.playButton.setDisable(true);
        this.deleteButton.setDisable(true);
    }

    private Label createLabel(String operation) {
        Label label = new Label(operation);
        label.setId(operation);
        label.setMnemonicParsing(false);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setText(bundle.getString("Operations." + operation));
        label.setAlignment(Pos.BASELINE_LEFT);
        label.getStyleClass().add("operation-label");
        return label;
    }

    @Override
    public void update(EventType eventType) {
        Runnable action = this.onEventDoActionMap.get(eventType);
        if (action != null)
            action.run();

    }
}
