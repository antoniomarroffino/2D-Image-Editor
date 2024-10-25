package ch.supsi.imageEditor.frontend.view.popup.error;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Objects;

public class ErrorViewPopUp implements ErrorViewInterface{
    private static ErrorViewPopUp instance = null;
    private final static String logoPath = "/images/logoApp.png";

    private ErrorViewPopUp(){}

    public static ErrorViewPopUp getInstance(){
        return instance == null ? instance = new ErrorViewPopUp() : instance;
    }

    @Override
    public void showPopUpError(String exceptionName, String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Region dialogPane = alert.getDialogPane();
        dialogPane.getStyleClass().add("custom-alert");
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("error-ok-button");
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/dark-theme.css")).toExternalForm());
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(logoPath))));
        alert.setTitle("Image Editor 2D - " + exceptionName);
        alert.setHeaderText("Error: " + exceptionName);
        alert.setContentText(error);
        alert.showAndWait();
    }
}
