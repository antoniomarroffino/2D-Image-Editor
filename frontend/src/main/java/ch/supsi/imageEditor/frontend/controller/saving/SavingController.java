package ch.supsi.imageEditor.frontend.controller.saving;

import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.saving.SavingModel;
import ch.supsi.imageEditor.frontend.model.saving.SavingModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXMLInterface;

import java.io.File;

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
    public boolean openImage(Component component) {
        File openFile = this.savingViewFXML.getOpenFile(this.savingModel.getSupportedFormats());
        if (openFile != null) {
            System.out.println(openFile.getAbsolutePath());
            //savingGameModel.setNewSavingGameFile(openFile);
            //return loadGame();
        }
        return false;
    }
}
