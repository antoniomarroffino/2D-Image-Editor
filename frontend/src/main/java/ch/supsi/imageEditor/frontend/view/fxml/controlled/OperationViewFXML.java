package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.adapter.ButtonAdapter;
import ch.supsi.imageEditor.frontend.controller.observer.EventOnApplication;
import ch.supsi.imageEditor.frontend.controller.observer.HandleServiceInterface;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.operation.OperationModelInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class OperationViewFXML implements ControlledFxView {
    private static final String PathResourceFXML = "/operations.fxml";
    private static OperationViewFXML instance = null;
    private static ResourceBundle bundle;
    private final Map<EventType, Runnable> onEventDoActionMap;
    private HandleServiceInterface handleService;
    private OperationModelInterface operationModel;
    @FXML
    private Pane operationPane;

    @FXML
    private ScrollPane operationScrollPane;

    @FXML
    private VBox operationVBox;

    private OperationViewFXML() {
        this.onEventDoActionMap = new HashMap<>();
    }

    public static OperationViewFXML getInstance(ResourceBundle resourceBundle) {
        if (instance == null) {
            instance = new OperationViewFXML();
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
        return this.operationPane;
    }

    @Override
    public void initialize(HandleServiceInterface handleService, AbstractModel model) {
        this.handleService = handleService;
        this.operationModel = (OperationModelInterface) model;
        this.fillMap();
    }

    private void fillMap() {
        this.onEventDoActionMap.put(EventType.OPEN_IMAGE, this::createSupportedOperationsButtons);
    }

    @Override
    public void update(EventType eventType) {
        Runnable action = this.onEventDoActionMap.get(eventType);
        if (action != null)
            action.run();
    }

    private void createSupportedOperationsButtons() {
        this.clearOperations();
        for (String supportedOperation : this.operationModel.getSupportedOperations()) {
            Button button = createButton(supportedOperation);
            this.operationVBox.getChildren().add(button);
        }
    }

    private Button createButton(String operation) {
        Button button = new Button(operation);
        button.setId(operation);
        button.setMnemonicParsing(false);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setText(bundle.getString("Operations." + operation));
        button.setAlignment(Pos.BASELINE_LEFT);
        button.getStyleClass().add("operation-button");
        button.setOnAction(event -> this.handleService.notify(EventOnApplication.CLICK_OPERATION, new ButtonAdapter((Button) event.getSource())));
        return button;
    }

    private void clearOperations() {
        this.operationVBox.getChildren().clear();
    }
}
