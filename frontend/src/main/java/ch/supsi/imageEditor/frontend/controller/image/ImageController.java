package ch.supsi.imageEditor.frontend.controller.image;

import ch.supsi.imageEditor.backend.application.observer.EventListener;
import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.exception.ImageTooBigException;
import ch.supsi.imageEditor.frontend.model.image.ImageModel;
import ch.supsi.imageEditor.frontend.model.image.ImageModelInterface;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModel;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;
import ch.supsi.imageEditor.frontend.view.popup.error.ErrorViewInterface;
import ch.supsi.imageEditor.frontend.view.popup.error.ErrorViewPopUp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageController implements ImageControllerInterface, EventListener {
    private static ImageController instance = null;
    private final PubSubModelInterface pubSubModel;
    private final ImageModelInterface imageModel;
    private final ErrorViewInterface errorView;
    private List<DataView> views;
    private final Map<EventType, Runnable> onEventDoActionMap;

    private ImageController() {
        this.pubSubModel = PubSubModel.getInstance();
        this.pubSubModel.subscribe(EventType.OPEN_IMAGE, this);
        this.pubSubModel.subscribe(EventType.RUN_PIPELINE, this);
        this.pubSubModel.subscribe(EventType.CLOSE_IMAGE, this);
        this.imageModel = ImageModel.getInstance();
        this.errorView = ErrorViewPopUp.getInstance();
        this.onEventDoActionMap = new HashMap<>();
    }

    public static ImageController getInstance() {
        return instance == null ? instance = new ImageController() : instance;
    }

    public void initialize(List<DataView> views) {
        this.views = views;
        this.onEventDoActionMap.put(EventType.OPEN_IMAGE, this::openImage);
        this.onEventDoActionMap.put(EventType.CLOSE_IMAGE, this::closeImage);
    }

    @Override
    public void update(EventType eventType) {
        Runnable action = onEventDoActionMap.get(eventType);
        if (action != null)
            action.run();

        for (DataView view : this.views)
            view.update(eventType);
    }

    private void openImage() {
        try {
            this.imageModel.loadCurrentImage();
        } catch (ImageTooBigException e) {
            this.errorView.showPopUpError(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    private void closeImage() {
        this.imageModel.closeCurrentImage();
    }
}
