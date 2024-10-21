package ch.supsi.imageEditor.frontend.model.saving;

import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public interface SavingModelInterface {
    Set<String> getSupportedFormats();

    void setNewSavingFile(File openFile);

    void loadImage(File openFile) throws FormatNotSupportedException, IOException;
}
