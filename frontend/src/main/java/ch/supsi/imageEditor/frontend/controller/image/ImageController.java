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

import java.util.List;

public class ImageController implements ImageControllerInterface, EventListener {
    private static ImageController instance = null;
    private final PubSubModelInterface pubSubModel;
    private final ImageModelInterface imageModel;
    private List<DataView> views;
    private final ErrorViewInterface errorView;

    private ImageController() {
        this.pubSubModel = PubSubModel.getInstance();
        this.pubSubModel.subscribe(EventType.LOAD_IMAGE, this);
        this.imageModel = ImageModel.getInstance();
        this.errorView = ErrorViewPopUp.getInstance();
    }

    public static ImageController getInstance() {
        return instance == null ? instance = new ImageController() : instance;
    }

    public void initialize(List<DataView> views) {
        this.views = views;
    }

    @Override
    public void update(EventType eventType) {
        try {
            this.imageModel.loadCurrentImage();
            for (DataView view : this.views)
                view.update(eventType);
        } catch (ImageTooBigException e) {
            this.errorView.showPopUpError(e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
