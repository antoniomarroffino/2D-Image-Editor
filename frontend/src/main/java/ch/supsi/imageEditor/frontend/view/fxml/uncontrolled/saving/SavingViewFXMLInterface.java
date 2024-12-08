package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving;

import javafx.stage.Stage;

import java.io.File;
import java.util.ResourceBundle;
import java.util.Set;

public interface SavingViewFXMLInterface {
    void initialize(ResourceBundle resourceBundle);

    File getOpenFile(Set<String> supportedFormat);

    void setMainStage(Stage mainStage);

    File getSaveFile(Set<String> supportedFormats);

    void showSaveConfirmationPopup(Runnable handleYes, Runnable handleNo);
}
