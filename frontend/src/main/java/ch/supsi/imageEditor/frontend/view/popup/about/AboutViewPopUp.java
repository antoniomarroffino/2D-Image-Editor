package ch.supsi.imageEditor.frontend.view.popup.about;

import ch.supsi.imageEditor.frontend.model.about.AboutModelInterface;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.ResourceBundle;

public class AboutViewPopUp implements AboutViewInterface {
    protected static AboutViewPopUp instance = null;
    private static final int LOGO_HEIGHT = 75;
    private static final int LOGO_WIDTH = 75;
    private static ResourceBundle resourceBundle;
    private AboutModelInterface aboutModel;

    protected AboutViewPopUp() {
    }

    public static AboutViewPopUp getInstance(ResourceBundle resBundle) {
        resourceBundle = resBundle;
        return instance == null ? instance = new AboutViewPopUp() : instance;
    }

    @Override
    public void initialize(AboutModelInterface aboutModel) {
        this.aboutModel = aboutModel;
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
        logoAppImageView.setFitWidth(LOGO_WIDTH);

        Region dialogPane = infoView.getDialogPane();
        dialogPane.setId("aboutAlert");
        dialogPane.getStyleClass().add("custom-alert");
        Button okButton = (Button) infoView.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("info-ok-button");
        infoView.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/dark-theme.css")).toExternalForm());

        infoView.setGraphic(logoAppImageView);
        infoView.setTitle(aboutModel.getTitle());
        infoView.setHeaderText(resourceBundle.getString("About.header") + aboutModel.getHeaderText());
        infoView.setContentText(resourceBundle.getString("About.version") + aboutModel.getVersion() + "\n"
                + resourceBundle.getString("About.projectName") + aboutModel.getProjectName() + "\n"
                + resourceBundle.getString("About.buildDate") + aboutModel.getBuiltDate() + "\n"
                + resourceBundle.getString("About.developers") + aboutModel.getDevelopersName());
        return infoView;
    }
}
