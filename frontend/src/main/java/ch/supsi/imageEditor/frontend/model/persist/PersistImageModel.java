package ch.supsi.imageEditor.frontend.model.persist;

import ch.supsi.imageEditor.backend.application.image.ImageController;
import ch.supsi.imageEditor.backend.application.image.ImageControllerInterface;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class PersistImageModel implements PersistImageModelInterface {
    private static PersistImageModel instance = null;

    private final ImageControllerInterface imageController;
    private File currentSavingFile;
    private boolean isAlreadySaved;

    private PersistImageModel() {
        this.imageController = ImageController.getInstance();
        this.currentSavingFile = null;
        this.isAlreadySaved = false;
    }

    public static PersistImageModel getInstance() {
        return instance == null ? instance = new PersistImageModel() : instance;
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
    public boolean isAlreadySave() {
        return this.isAlreadySaved;
    }

    @Override
    public void setAlreadySave(boolean isAlreadySaved) {
        this.isAlreadySaved = isAlreadySaved;
    }

    @Override
    public void loadImage(File openFile) throws FormatNotSupportedException, IOException, ImageHeaderUncorrectException {
        this.imageController.loadImage(openFile.getPath());
    }

    @Override
    public void writeImage() {
        this.imageController.writeImage(this.currentSavingFile);
    }
}
