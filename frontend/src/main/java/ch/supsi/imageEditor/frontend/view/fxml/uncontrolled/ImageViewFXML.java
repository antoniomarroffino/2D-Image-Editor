package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.OperationViewFXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class ImageViewFXML implements UncontrolledFxView {
    private static ImageViewFXML instance = null;
    private static final String PathResourceFXML = "/imagewindow.fxml";

    @FXML
    private Pane imagePane;

    private ImageViewFXML() {
    }

    public static ImageViewFXML getInstance() {
        if (instance == null) {
            instance = new ImageViewFXML();
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
        return this.imagePane;
    }

    @Override
    public void initialize(AbstractModel model, HandleViewModelInterface handleViewModel) {

    }
    @Override
    public void update(EventType eventType) {

    }
}
