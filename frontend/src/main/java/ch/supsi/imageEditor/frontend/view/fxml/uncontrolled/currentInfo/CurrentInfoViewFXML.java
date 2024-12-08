package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.currentInfo;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.persist.PersistImageModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.operation.OperationViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.UncontrolledFxView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CurrentInfoViewFXML implements UncontrolledFxView {
    private static final String PathResourceFXML = "/currentinfo.fxml";
    protected static CurrentInfoViewFXML instance = null;
    private final Map<EventType, Runnable> onEventDoActionMap;
    private final SimpleDateFormat sdf;
    private PersistImageModelInterface persistImageModel;
    private String nameLabelDefaultText;
    private String formatLabelDefaultText;
    private String dimensionLabelDefaultText;
    private String modificationDateLabelDefaultText;

    @FXML
    private Pane currentInfoPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label formatLabel;

    @FXML
    private Label dimensionLabel;

    @FXML
    private Label modificationDateLabel;

    protected CurrentInfoViewFXML() {
        this.onEventDoActionMap = new HashMap<>();
        this.sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public static CurrentInfoViewFXML getInstance(ResourceBundle resourceBundle) {
        if (instance == null) {
            instance = new CurrentInfoViewFXML();
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

    Map<EventType, Runnable> getOnEventDoActionMap() {
        return this.onEventDoActionMap;
    }

    SimpleDateFormat getSdf() {
        return this.sdf;
    }

    @Override
    public Node getNode() {
        return this.currentInfoPane;
    }

    @Override
    public void initialize(AbstractModel model) {
        this.persistImageModel = (PersistImageModelInterface) model;
        this.fillMap();
        this.nameLabelDefaultText = this.nameLabel.getText();
        this.formatLabelDefaultText = this.formatLabel.getText();
        this.dimensionLabelDefaultText = this.dimensionLabel.getText();
        this.modificationDateLabelDefaultText = this.modificationDateLabel.getText();
    }

    private void fillMap() {
        this.onEventDoActionMap.put(EventType.OPEN_IMAGE, this::showDetails);
        this.onEventDoActionMap.put(EventType.SAVE_IMAGE, this::changeLastModifiedDate);
        this.onEventDoActionMap.put(EventType.CLOSE_IMAGE, this::hideDetails);
    }

    @Override
    public void update(EventType eventType) {
        Runnable action = this.onEventDoActionMap.get(eventType);
        if (action != null)
            action.run();
    }

    private void showDetails() {
        File currentFile = this.persistImageModel.getCurrentFile();
        this.nameLabel.setText(this.nameLabelDefaultText + " " + currentFile.getName().substring(0, currentFile.getName().lastIndexOf(".")));
        this.formatLabel.setText(this.formatLabelDefaultText + " " + currentFile.getAbsolutePath().substring(currentFile.getAbsolutePath().lastIndexOf(".") + 1));
        this.dimensionLabel.setText(this.dimensionLabelDefaultText + " " + currentFile.length() + " bytes");
        this.modificationDateLabel.setText(this.modificationDateLabelDefaultText + " " + this.sdf.format(currentFile.lastModified()));
    }

    private void changeLastModifiedDate() {
        File currentFile = this.persistImageModel.getCurrentFile();
        this.modificationDateLabel.setText(this.modificationDateLabelDefaultText + " " + this.sdf.format(currentFile.lastModified()));
    }

    private void hideDetails() {
        this.nameLabel.setText(this.nameLabelDefaultText);
        this.formatLabel.setText(this.formatLabelDefaultText);
        this.dimensionLabel.setText(this.dimensionLabelDefaultText);
        this.modificationDateLabel.setText(this.modificationDateLabelDefaultText);
    }
}
