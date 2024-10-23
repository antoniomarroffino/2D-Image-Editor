package ch.supsi.imageEditor.frontend.controller.saving;

import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.exception.ImageTooBigException;
import ch.supsi.imageEditor.frontend.model.image.ImageModel;
import ch.supsi.imageEditor.frontend.model.image.ImageModelInterface;
import ch.supsi.imageEditor.frontend.model.saving.SavingModel;
import ch.supsi.imageEditor.frontend.model.saving.SavingModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXMLInterface;

import java.io.File;
import java.io.IOException;

public class SavingController implements SavingControllerInterface {
    private static SavingController instance = null;
    private final SavingModelInterface savingModel;

    private final SavingViewFXMLInterface savingViewFXML;

    private SavingController() {
        this.savingViewFXML = SavingViewFXML.getInstance();
        this.savingModel = SavingModel.getInstance();
    }

    public static SavingController getInstance() {
        return instance == null ? instance = new SavingController() : instance;
    }

    @Override
    public void openImage(Component component) {
        File openFile = this.savingViewFXML.getOpenFile(this.savingModel.getSupportedFormats());
        if (openFile != null)
            loadImage(openFile);
    }

    private void loadImage(File openFile) {
        try {
            this.savingModel.loadImage(openFile);
            this.savingModel.setNewSavingFile(openFile);
        } catch (FormatNotSupportedException | IOException | ImageHeaderUncorrectException e) {
            System.out.println(e.getMessage());
        }
    }
}
