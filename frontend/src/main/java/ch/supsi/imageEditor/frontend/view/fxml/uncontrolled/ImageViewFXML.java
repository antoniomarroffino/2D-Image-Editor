package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.image.ImageModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.OperationViewFXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ImageViewFXML implements UncontrolledFxView {
    private static final String PathResourceFXML = "/imagewindow.fxml";
    private static ImageViewFXML instance = null;
    private final Map<EventType, Runnable> onEventDoActionMap;
    private ImageModelInterface imageModel;
    @FXML
    private ScrollPane imageScrollPane;
    @FXML
    private Label placeHolderText;

    private ImageViewFXML() {
        this.onEventDoActionMap = new HashMap<>();
    }

    public static ImageViewFXML getInstance(ResourceBundle resourceBundle) {
        if (instance == null) {
            instance = new ImageViewFXML();
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
        return this.imageScrollPane;
    }

    @Override
    public void initialize(AbstractModel model) {
        this.imageModel = (ImageModelInterface) model;
        this.onEventDoActionMap.put(EventType.OPEN_IMAGE, this::display);
        this.onEventDoActionMap.put(EventType.RUN_PIPELINE, this::display);
    }

    @Override
    public void update(EventType eventType) {
        Runnable action = this.onEventDoActionMap.get(eventType);
        if (action != null)
            action.run();
    }

    private void display() {
        imageScrollPane.setContent(new Group(getCanvasFromImage()));
        placeHolderText.setVisible(false);
    }

    private Canvas getCanvasFromImage() {
        int height = this.imageModel.getHeightImage();
        int width = this.imageModel.getWidthImage();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int x = 0; x < height; x++)
            for (int y = 0; y < width; y++) {
                gc.setFill(javafx.scene.paint.Color.rgb(this.imageModel.getPixelRed(x, y),
                        this.imageModel.getPixelGreen(x, y),
                        this.imageModel.getPixelBlue(x, y)));
                gc.fillRect(y, x, 1, 1);
            }
        return canvas;
    }
}
