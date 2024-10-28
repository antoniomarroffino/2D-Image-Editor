package ch.supsi.imageEditor.frontend.controller.persisting;

import ch.supsi.imageEditor.backend.application.observer.EventListener;
import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.persist.PersistImageModel;
import ch.supsi.imageEditor.frontend.model.persist.PersistImageModelInterface;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModel;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXMLInterface;
import ch.supsi.imageEditor.frontend.view.popup.error.ErrorViewInterface;
import ch.supsi.imageEditor.frontend.view.popup.error.ErrorViewPopUp;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PersistImageController implements PersistImageControllerInterface, EventListener {
    private static PersistImageController instance = null;
    private final PersistImageModelInterface persistImageModel;
    private final PubSubModelInterface pubSubModel;
    private final SavingViewFXMLInterface savingViewFXML;
    private final ErrorViewInterface errorView;
    private List<DataView> views;

    private PersistImageController() {
        this.savingViewFXML = SavingViewFXML.getInstance();
        this.pubSubModel = PubSubModel.getInstance();
        this.persistImageModel = PersistImageModel.getInstance();
        this.errorView = ErrorViewPopUp.getInstance();
        this.pubSubModel.subscribe(EventType.RUN_PIPELINE, this);
        this.pubSubModel.subscribe(EventType.SAVE_IMAGE, this);
    }

    public static PersistImageController getInstance() {
        return instance == null ? instance = new PersistImageController() : instance;
    }

    @Override
    public void initialize(List<DataView> views) {
        this.views = views;
    }

    @Override
    public void openImage(Component component) {
        File openFile = this.savingViewFXML.getOpenFile(this.persistImageModel.getSupportedFormats());
        if (openFile != null)
            loadImage(openFile);
    }

    @Override
    public void saveImage(Component component) {
        if (!this.persistImageModel.isAlreadySave())
            save();
    }

    @Override
    public void saveImageAs(Component component) {
        File saveImageFile = this.savingViewFXML.getSaveFile(this.persistImageModel.getSupportedFormats());
        if (saveImageFile != null) {
            this.persistImageModel.setNewSavingFile(saveImageFile);
            save();
        }
    }

    private void save() {
        this.persistImageModel.writeImage();
        this.persistImageModel.setAlreadySave(true);
    }

    private void loadImage(File openFile) {
        try {
            this.persistImageModel.loadImage(openFile);
            this.persistImageModel.setNewSavingFile(openFile);
            this.persistImageModel.setAlreadySave(true);
        } catch (FormatNotSupportedException | IOException | ImageHeaderUncorrectException e) {
            this.errorView.showPopUpError(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public void update(EventType eventType) {
        for (DataView view : this.views)
            view.update(eventType);
    }
}
