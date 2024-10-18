package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModelInterface;
import ch.supsi.imageEditor.frontend.model.language.LanguageModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.OperationViewFXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class InfobarViewFXML implements UncontrolledFxView {
    private static InfobarViewFXML instance = null;
    private static final String PathResourceFXML = "/infobar.fxml";
    private static LanguageModelInterface languageModel;
    private static final Map<EventType, String> eventDescription;
    private static ResourceBundle bundle;

    @FXML
    private Pane infoBarPane;

    @FXML
    private VBox contentVBox;

    static {
        eventDescription = new HashMap<>();
    }

    private InfobarViewFXML() {
    }

    public static InfobarViewFXML getInstance(ResourceBundle resourceBundle) {
        if (instance == null) {
            instance = new InfobarViewFXML();
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

    private static void fillMap(ResourceBundle resourceBundle) {
        eventDescription.put(EventType.CHANGE_LANGUAGE, resourceBundle.getString("Infobar.changeLanguage"));
    }

    @Override
    public Node getNode() {
        return this.infoBarPane;
    }

    @Override
    public void initialize(AbstractModel model, HandleViewModelInterface handleViewModel) {
        handleViewModel.subscribe(EventType.CHANGE_LANGUAGE, this);
        languageModel = (LanguageModelInterface) model;
        fillMap(bundle);
    }

    @Override
    public void update(EventType eventType) {
        Label newLabel = new Label();
        newLabel.setText(eventDescription.get(eventType));
        newLabel.setWrapText(true);
        this.contentVBox.getChildren().add(0, newLabel);
    }
}
