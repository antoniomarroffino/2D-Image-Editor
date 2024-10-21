package ch.supsi.imageEditor.frontend.controller.image;

import ch.supsi.imageEditor.backend.application.observer.EventListener;
import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModel;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModelInterface;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModel;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

public class ImageController implements ImageControllerInterface, EventListener {
    private static ImageController instance = null;
    private final HandleViewModelInterface handleViewModel;
    private final PubSubModelInterface pubSubModel;

    private ImageController() {
        this.handleViewModel = HandleViewModel.getInstance();
        this.pubSubModel = PubSubModel.getInstance();
        this.pubSubModel.subscribe(EventType.LOAD_IMAGE, this);
    }

    public static ImageController getInstance() {
        return instance == null ? instance = new ImageController() : instance;
    }

    @Override
    public void update(EventType eventType) {
        for (DataView view : this.handleViewModel.getViewsOnEventType(eventType))
            view.update(eventType);
    }
}
