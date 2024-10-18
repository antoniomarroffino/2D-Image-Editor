package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.controller.observer.HandleServiceInterface;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModelInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class OperationViewFXML implements OperationViewFXMLInterface {
    private static OperationViewFXML instance = null;
    private static final String PathResourceFXML = "/operations.fxml";
    private HandleServiceInterface handleService;

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
    }

    @Override
    public void update(EventType eventType) {

    }

    @Override
    public void createOperationMenuItem(Set<String> supportedOperations) {
        for (String operation : supportedOperations) {
            Button button = new Button(operation);
            button.setId(operation);
            button.setMnemonicParsing(false);
            //item.setText(bundle.getString("MenuBar." + supportedLanguage));
            //item.setOnAction(event -> this.handleService.notify(EventOnApplication.CHANGE_LANGUAGE, new MenuItemAdapter((MenuItem) event.getSource())));
            this.operationVBox.getChildren().add(button);
        }
    }
}
