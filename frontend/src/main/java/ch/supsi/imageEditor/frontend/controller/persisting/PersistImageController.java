package ch.supsi.imageEditor.frontend.controller.persisting;

import ch.supsi.imageEditor.backend.application.observer.EventListener;
import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.exit.ExitModel;
import ch.supsi.imageEditor.frontend.model.exit.ExitModelInterface;
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
    protected static PersistImageController instance = null;
    private final PersistImageModelInterface persistImageModel;
    private final PubSubModelInterface pubSubModel;
    private final ExitModelInterface exitModel;
    private final SavingViewFXMLInterface savingViewFXML;
    private final ErrorViewInterface errorView;
    private List<DataView> views;

    private String filePathOpenRecent;

    protected PersistImageController() {
        this.savingViewFXML = SavingViewFXML.getInstance();
        this.pubSubModel = PubSubModel.getInstance();
        this.persistImageModel = PersistImageModel.getInstance();
        this.exitModel = ExitModel.getInstance();
        this.errorView = ErrorViewPopUp.getInstance();
        this.pubSubModel.subscribe(EventType.SAVE_IMAGE, this);
    }

    public static PersistImageController getInstance() {
        return instance == null ? instance = new PersistImageController() : instance;
    }

    PersistImageModelInterface getPersistImageModel() {
        return this.persistImageModel;
    }

    PubSubModelInterface getPubSubModel() {
        return this.pubSubModel;
    }

    ExitModelInterface getExitModel() {
        return this.exitModel;
    }

    SavingViewFXMLInterface getSavingViewFXML() {
        return this.savingViewFXML;
    }

    ErrorViewInterface getErrorView() {
        return this.errorView;
    }

    @Override
    public void initialize(List<DataView> views) {
        this.views = views;
    }

    @Override
    public void requestSaveBeforeOpen(Component component) {
        if (this.persistImageModel.existCurrentFile() && !this.persistImageModel.isAlreadySave())
            this.savingViewFXML.showSaveConfirmationPopup(this::save, this::openImage);
        else
            this.openImage();
    }

    private void openImage() {
        File openFile = this.savingViewFXML.getOpenFile(this.persistImageModel.getSupportedFormats());
        if (openFile != null)
            loadImage(openFile);
    }

    @Override
    public void requestSaveBeforeOpenRecent(Component component) {
        this.filePathOpenRecent = component.getId();
        if (this.persistImageModel.existCurrentFile() && !this.persistImageModel.isAlreadySave())
            this.savingViewFXML.showSaveConfirmationPopup(this::save, this::openRecentImage);
        else
            this.openRecentImage();
    }

    private void openRecentImage() {
        File fileToOpen = new File(this.filePathOpenRecent);
        if (fileToOpen.exists())
            loadImage(fileToOpen);
    }

    @Override
    public void saveImage(Component component) {
        if (!this.persistImageModel.isAlreadySave())
            save();
    }

    @Override
    public void saveImageAs(Component component) {
        File saveImageFile = this.savingViewFXML.getSaveFile(this.persistImageModel.getSupportedFormats());
        if (saveImageFile != null)
            this.persistImageModel.writeImageAs(saveImageFile);
    }

    private void save() {
        this.persistImageModel.writeImage();
        this.persistImageModel.setAlreadySave(true);
    }

    private void loadImage(File openFile) {
        try {
            this.persistImageModel.setNewSavingFile(openFile);
            this.persistImageModel.setAlreadySave(true);
            this.persistImageModel.loadImage(openFile);
        } catch (FormatNotSupportedException | IOException | ImageHeaderUncorrectException e) {
            this.persistImageModel.setNewSavingFile(null);
            this.persistImageModel.setAlreadySave(false);
            this.errorView.showPopUpError(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public void requestSaveBeforeClose(Component component) {
        if (!this.persistImageModel.isAlreadySave())
            this.savingViewFXML.showSaveConfirmationPopup(this::save, this::closeImage);
        else
            this.closeImage();
    }

    @Override
    public void requestSaveBeforeQuit(Component component) {
        if (this.persistImageModel.existCurrentFile() && !this.persistImageModel.isAlreadySave())
            this.savingViewFXML.showSaveConfirmationPopup(this::save, this.exitModel::closeApplication);
        else
            this.exitModel.closeApplication().run();
    }

    private void closeImage() {
        this.persistImageModel.closeImage();
    }

    @Override
    public void update(EventType eventType) {
        for (DataView view : this.views)
            view.update(eventType);
    }
}
