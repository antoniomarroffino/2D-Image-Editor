package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.PNM.Pixel;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModelInterface;
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

    @Override
    public Node getNode() {
        return this.imageStackPane;
    }

    @Override
    public void initialize(AbstractModel model, HandleViewModelInterface handleViewModel) {
        handleViewModel.subscribe(EventType.LOAD_IMAGE, this);
        this.imageModel = (ImageModelInterface) model;
        this.onEventDoActionMap.put(EventType.LOAD_IMAGE, this::display);
    }

    @Override
    public void update(EventType eventType) {
        Runnable action = this.onEventDoActionMap.get(eventType);
        if (action != null)
            action.run();
    }

    private void display() {
        AbstractImage img = this.imageModel.getImage();
        imageStackPane.getChildren().add(getCanvasFromImage(img));
        placeHolderText.setVisible(false);
    }

    private Canvas getCanvasFromImage(AbstractImage image) {
        int height = image.getHeight(), width = image.getWidth();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Pixel[][] pixel = image.getPixel();
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                gc.setFill(javafx.scene.paint.Color.rgb(pixel[y][x].getRed(), pixel[y][x].getGreen(), pixel[y][x].getBlue()));
                gc.fillRect(x, y, 1, 1);
            }
        return canvas;
    }
}
