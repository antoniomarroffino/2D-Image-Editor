package ch.supsi.imageEditor.frontend.view.fxml.controlled;

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

public class OperationViewFXML implements ControlledFxView {
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

    public static OperationViewFXML getInstance() {
        if (instance == null) {
            instance = new OperationViewFXML();
            try {
                URL fxmlUrl = OperationViewFXML.class.getResource(PathResourceFXML);
                if (fxmlUrl != null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
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
    public void initialize(HandleServiceInterface handleService, AbstractModel model) {
        this.handleService = handleService;
    }
}
