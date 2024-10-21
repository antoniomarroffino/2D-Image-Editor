package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Set;

public class SavingViewFXML implements SavingViewFXMLInterface {
    private static SavingViewFXML instance = null;
    private Stage mainStage;

    private SavingViewFXML() {
    }

    public static SavingViewFXML getInstance() {
        return instance == null ? instance = new SavingViewFXML() : instance;
    }

    @Override
    public File getOpenFile(Set<String> supportedFormat) {
        FileChooser fileChooser = new FileChooser();
        //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(supportedFormat.spliterator().toString());
        //.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showOpenDialog(mainStage);
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
