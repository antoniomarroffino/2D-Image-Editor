package ch.supsi.imageEditor.frontend.view.popup.about;

import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.about.AboutModel;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.UncontrolledView;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class AboutViewPopUp implements AboutViewInterface, UncontrolledView {
    private static AboutViewPopUp instance = null;
    private AboutModel aboutModel;
    private final static String PATH_LOGO_APP_IMAGE = "/images/logoApp.png";

    private AboutViewPopUp() {
    }

    public static AboutViewPopUp getInstance() {
        return instance == null ? instance = new AboutViewPopUp() : instance;
    }

    @Override
    public void showAboutInformation() {
        Alert infoView = aboutModel.getAlertOfInfo();
        Stage stage = (Stage) infoView.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(PATH_LOGO_APP_IMAGE))));
        infoView.showAndWait();
    }

    @Override
    public void initialize(AbstractModel model) {
        this.aboutModel = (AboutModel) model;
    }
}
