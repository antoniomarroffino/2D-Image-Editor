package ch.supsi.imageEditor.frontend.view.popup.about;

import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.about.AboutModel;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.UncontrolledView;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AboutViewPopUp implements AboutViewInterface, UncontrolledView {
    private static AboutViewPopUp instance = null;
    private AboutModel aboutModel;
    private static final int LOGO_HEIGHT = 75;
    private static final int LOGO_WIDHT = 75;

    private AboutViewPopUp() {
    }

    public static AboutViewPopUp getInstance() {
        return instance == null ? instance = new AboutViewPopUp() : instance;
    }

    @Override
    public void initialize(AbstractModel model) {
        this.aboutModel = (AboutModel) model;
    }

    @Override
    public void showAboutInformation() {
        Alert infoView = getAlertOfInfo();
        Stage stage = (Stage) infoView.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(aboutModel.getLogoInputStream()));
        infoView.showAndWait();
    }

    private Alert getAlertOfInfo() {
        Alert infoView = new Alert(Alert.AlertType.INFORMATION);
        Image logoAppImage = new Image(aboutModel.getLogoInputStream());
        ImageView logoAppImageView = new ImageView(logoAppImage);
        logoAppImageView.setFitHeight(LOGO_HEIGHT);
        logoAppImageView.setFitWidth(LOGO_WIDHT);

        infoView.setGraphic(logoAppImageView);
        infoView.setTitle(aboutModel.getTitle());
        infoView.setHeaderText(aboutModel.getHeaderText());
        infoView.setContentText(aboutModel.getContextText());
        return infoView;
    }
}
