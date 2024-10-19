package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.adapter.ButtonAdapter;
import ch.supsi.imageEditor.frontend.adapter.MenuItemAdapter;
import ch.supsi.imageEditor.frontend.controller.observer.EventOnApplication;
import ch.supsi.imageEditor.frontend.controller.observer.HandleServiceInterface;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModelInterface;
import ch.supsi.imageEditor.frontend.model.operation.OperationModelInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class OperationViewFXML implements ControlledFxView, OperationViewFXMLInterface {
    private static OperationViewFXML instance = null;
    private static final String PathResourceFXML = "/operations.fxml";
    private HandleServiceInterface handleService;
    private static ResourceBundle bundle;

    @FXML
    private Pane operationPane;

    @FXML
    private ScrollPane operationScrollPane;

    @FXML
    private VBox operationVBox;

    private OperationViewFXML() {
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
    public void initialize(HandleServiceInterface handleService, AbstractModel model, HandleViewModelInterface handleViewModel) {
        this.handleService = handleService;
        this.createBehaviour();
    }

    private void createBehaviour() {
        //
    }

    @Override
    public void update(EventType eventType) {

    }

    @Override
    public void createSupportedOperationsButtons(OperationModelInterface operationModel) {
        Set<String> supportedOperations = operationModel.getSupportedOperations();
        for (String supportedOperation : supportedOperations) {
            Button button = new Button(supportedOperation);
            button.setId(supportedOperation);
            button.setMnemonicParsing(false);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setText(bundle.getString("Operations." + supportedOperation));
            button.setAlignment(Pos.BASELINE_LEFT);
            button.setOnAction(event -> this.handleService.notify(EventOnApplication.CLICK_OPERATION, new ButtonAdapter((Button) event.getSource())));
            this.operationVBox.getChildren().add(button);
        }
    }
}
