package ch.supsi.imageEditor.frontend.model.saving;

import ch.supsi.imageEditor.backend.application.image.ImageController;
import ch.supsi.imageEditor.backend.application.image.ImageControllerInterface;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class SavingModel implements SavingModelInterface {
    private static SavingModel instance = null;

    private final ImageControllerInterface imageController;
    private File currentSavingFile = null;

    private SavingModel() {
        this.imageController = ImageController.getInstance();
    }

    public static SavingModel getInstance() {
        return instance == null ? instance = new SavingModel() : instance;
    }

    @Override
    public Set<String> getSupportedFormats() {
        return this.imageController.getSupportedFormat();
    }

    @Override
    public void setNewSavingFile(File openFile) {
        this.currentSavingFile = openFile;
    }

    @Override
    public void loadImage(File openFile) throws FormatNotSupportedException, IOException, ImageHeaderUncorrectException {
        this.imageController.loadImage(openFile.getPath());
    }
}
