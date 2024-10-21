package ch.supsi.imageEditor.frontend.model.saving;

import ch.supsi.imageEditor.backend.application.image.ImageController;
import ch.supsi.imageEditor.backend.application.image.ImageControllerInterface;

import java.util.Set;

public class SavingModel implements SavingModelInterface {
    private static SavingModel instance = null;

    private final ImageControllerInterface imageController;

    private SavingModel() {
        this.imageController = ImageController.getInstance();
    }

    public static SavingModel getInstance() {
        return instance == null ? instance = new SavingModel() : instance;
    }

    public Set<String> getSupportedFormats() {
        return this.imageController.getSupportedFormat();
    }
}
