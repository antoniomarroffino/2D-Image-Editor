package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.language.LanguageModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.OperationViewFXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class InfobarViewFXML implements UncontrolledFxView {
    private static final String PathResourceFXML = "/infobar.fxml";
    private static final Map<EventType, String> eventDescription;
    private static InfobarViewFXML instance = null;
    private static LanguageModelInterface languageModel;
    private static ResourceBundle bundle;

    static {
        eventDescription = new HashMap<>();
    }

    @FXML
    private Pane infoBarPane;
    @FXML
    private TextArea infoBarTextArea;

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
        eventDescription.put(EventType.LOAD_IMAGE, resourceBundle.getString("Infobar.loadImage"));
    }

    @Override
    public Node getNode() {
        return this.infoBarPane;
    }

    @Override
    public void initialize(AbstractModel model) {
        languageModel = (LanguageModelInterface) model;
        fillMap(bundle);
        this.infoBarTextArea.setWrapText(true);
        this.infoBarTextArea.setEditable(false);
    }

    @Override
    public void update(EventType eventType) {
        StringBuilder newText = new StringBuilder();
        String previousText = this.infoBarTextArea.getText();
        newText.append(eventDescription.get(eventType)).append("\n\n").append(previousText);
        this.infoBarTextArea.setText(newText.toString());
    }
}
