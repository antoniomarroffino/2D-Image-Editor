package ch.supsi.imageEditor.frontend.model.persist;

import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public interface PersistImageModelInterface {
    Set<String> getSupportedFormats();

    void setNewSavingFile(File openFile);

    boolean existCurrentFile();

    boolean isAlreadySave();

    void setAlreadySave(boolean isAlreadySaved);

    void loadImage(File openFile) throws FormatNotSupportedException, IOException, ImageHeaderUncorrectException;

    void writeImage();

    void closeImage();
}
