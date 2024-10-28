package ch.supsi.imageEditor.frontend.controller.persisting;

import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.persist.PersistImageImageModel;
import ch.supsi.imageEditor.frontend.model.persist.PersistImageModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXMLInterface;
import ch.supsi.imageEditor.frontend.view.popup.error.ErrorViewInterface;
import ch.supsi.imageEditor.frontend.view.popup.error.ErrorViewPopUp;

import java.io.File;
import java.io.IOException;

public class PersistImageImageController implements PersistImageControllerInterface {
    private static PersistImageImageController instance = null;
    private final PersistImageModelInterface persistImageModel;

    private final SavingViewFXMLInterface savingViewFXML;
    private final ErrorViewInterface errorView;

    private PersistImageImageController() {
        this.savingViewFXML = SavingViewFXML.getInstance();
        this.persistImageModel = PersistImageImageModel.getInstance();
        this.errorView = ErrorViewPopUp.getInstance();
    }

    public static PersistImageImageController getInstance() {
        return instance == null ? instance = new PersistImageImageController() : instance;
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
    }

    private void loadImage(File openFile) {
        try {
            this.persistImageModel.loadImage(openFile);
            this.persistImageModel.setNewSavingFile(openFile);
        } catch (FormatNotSupportedException | IOException | ImageHeaderUncorrectException e) {
            this.errorView.showPopUpError(e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
