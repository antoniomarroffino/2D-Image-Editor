package ch.supsi.imageEditor.frontend.controller.image;

import ch.supsi.imageEditor.backend.application.observer.EventListener;
import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModel;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

import java.util.List;

public class ImageController implements ImageControllerInterface, EventListener {
    private static ImageController instance = null;
    private final PubSubModelInterface pubSubModel;
    private List<DataView> views;

    private ImageController() {
        this.pubSubModel = PubSubModel.getInstance();
        this.pubSubModel.subscribe(EventType.LOAD_IMAGE, this);
    }

    public static ImageController getInstance() {
        return instance == null ? instance = new ImageController() : instance;
    }

    public void initialize(List<DataView> views) {
        this.views = views;
    }

    @Override
    public void update(EventType eventType) {
        for (DataView view : this.views)
            view.update(eventType);
    }
}
