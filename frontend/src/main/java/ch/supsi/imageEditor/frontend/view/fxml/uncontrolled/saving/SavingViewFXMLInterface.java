package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving;

import ch.supsi.imageEditor.frontend.view.fxml.controlled.ControlledFxView;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.ControlledView;
import javafx.stage.Stage;

import java.io.File;
import java.util.ResourceBundle;
import java.util.Set;

public interface SavingViewFXMLInterface {
    File getOpenFile(Set<String> supportedFormat);

    void setMainStage(Stage mainStage);

    File getSaveFile(Set<String> supportedFormats);

    void showSaveConfirmationPopup(Runnable handleYes, Runnable handleNo);
}
