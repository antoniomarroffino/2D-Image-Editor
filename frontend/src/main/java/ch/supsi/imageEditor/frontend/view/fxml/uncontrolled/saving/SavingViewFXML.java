package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

public class SavingViewFXML implements SavingViewFXMLInterface {
    private static SavingViewFXML instance = null;
    private Stage mainStage;
    private ResourceBundle resourceBundle;

    private SavingViewFXML() {
    }

    public static SavingViewFXML getInstance() {
        return instance == null ? instance = new SavingViewFXML() : instance;
    }

    @Override
    public void initialize(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public File getOpenFile(Set<String> supportedFormat) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Images Supported", supportedFormat.stream().map(String::toLowerCase).map(s -> "*." + s).toArray(String[]::new));
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showOpenDialog(this.mainStage);
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public File getSaveFile(Set<String> supportedFormats) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Images Supported", supportedFormats.stream().map(String::toLowerCase).map(s -> "*." + s).toArray(String[]::new));
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showSaveDialog(this.mainStage);
    }

    @Override
    public void showSaveConfirmationPopup(Runnable handleYes, Runnable handleNo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(this.resourceBundle.getString("SaveGamePopup.title"));
        alert.setHeaderText(this.resourceBundle.getString("SaveGamePopup.header"));
        alert.setContentText(this.resourceBundle.getString("SaveGamePopup.context"));

        ButtonType yesButton = new ButtonType(this.resourceBundle.getString("SaveGamePopup.buttonYes"));
        ButtonType noButton = new ButtonType(this.resourceBundle.getString("SaveGamePopup.buttonNo"));
        alert.getButtonTypes().setAll(yesButton, noButton);

        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/dark-theme.css")).toExternalForm());

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setOnCloseRequest(Event::consume);

        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                handleYes.run();    //SAVE
                handleNo.run();     //CLOSE / QUIT
            } else if (response == noButton) {
                handleNo.run();     //CLOSE / QUIT
            }
        });


    }
}
