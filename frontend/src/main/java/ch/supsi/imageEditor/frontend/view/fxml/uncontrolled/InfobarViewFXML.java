package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.OperationViewFXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.net.URL;

public class InfobarViewFXML implements UncontrolledFxView {
    private static InfobarViewFXML instance = null;
    private static final String PathResourceFXML = "/infobar.fxml";

    @FXML
    private ScrollPane infobarScrollPane;

    private InfobarViewFXML() {
    }

    public static InfobarViewFXML getInstance() {
        if (instance == null) {
            instance = new InfobarViewFXML();
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
        return this.infobarScrollPane;
    }

    @Override
    public void initialize(AbstractModel model, HandleViewModelInterface handleViewModel) {
        handleViewModel.subscribe(EventType.CHANGE_LANGUAGE, this);
    }

    @Override
    public void update(EventType eventType) {
        System.out.println(eventType.name());
    }
}
