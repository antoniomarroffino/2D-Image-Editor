package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving;

import javafx.stage.Stage;

import java.io.File;
import java.util.Set;

public interface SavingViewFXMLInterface {
    File getOpenFile(Set<String> supportedFormat);

    void setMainStage(Stage mainStage);
}
