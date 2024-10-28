package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.image.ImageModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.OperationViewFXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ImageViewFXML implements UncontrolledFxView {
    private static final String PathResourceFXML = "/imagewindow.fxml";
    private static ImageViewFXML instance = null;
    private final Map<EventType, Runnable> onEventDoActionMap;
    public double width;
    public double height;
    private ImageModelInterface imageModel;
    @FXML
    private StackPane imageStackPane;
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

    public static double getWidthOfPane() {
        if (instance != null)
            return instance.width;
        return 0;
    }

    public static double getHeightOfPane() {
        if (instance != null)
            return instance.height;
        return 0;
    }

    @Override
    public Node getNode() {
        return this.imageStackPane;
    }

    @Override
    public void initialize(AbstractModel model) {
        this.imageModel = (ImageModelInterface) model;
        this.onEventDoActionMap.put(EventType.OPEN_IMAGE, this::display);
        this.onEventDoActionMap.put(EventType.RUN_PIPELINE, this::display);
        this.onEventDoActionMap.put(EventType.CLOSE_IMAGE, this::closeImage);

        this.width = this.imageStackPane.getPrefWidth();
        this.height = this.imageStackPane.getPrefHeight();
    }

    @Override
    public void update(EventType eventType) {
        Runnable action = this.onEventDoActionMap.get(eventType);
        if (action != null)
            action.run();
    }

    private void display() {
        this.imageStackPane.getChildren().add(getCanvasFromImage());
        this.placeHolderText.setVisible(false);
    }

    private Canvas getCanvasFromImage() {
        int height = this.imageModel.getHeightImage();
        int width = this.imageModel.getWidthImage();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                gc.setFill(javafx.scene.paint.Color.rgb(this.imageModel.getPixelRed(x, y),
                        this.imageModel.getPixelGreen(x, y),
                        this.imageModel.getPixelBlue(x, y)));
                gc.fillRect(x, y, 1, 1);
            }
        return canvas;
    }

    private void closeImage(){
        this.imageStackPane.getChildren().removeIf(n -> n instanceof Canvas);
        this.placeHolderText.setVisible(true);
    }
}
