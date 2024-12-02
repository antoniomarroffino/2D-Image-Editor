package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving;

import ch.supsi.imageEditor.frontend.view.fxml.controlled.operation.OperationViewFXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class SavingViewFXML implements SavingViewFXMLInterface {
    protected static SavingViewFXML instance = null;
    private static final String PathResourceFXML = "/saveConfirmation.fxml";
    private final static String PATH_LOGO_APP_IMAGE = "/images/logoApp.png";
    private Stage mainStage;
    private static ResourceBundle bundle;

    @FXML
    private VBox confirmationVBox;
    @FXML
    private Button buttonYes;
    @FXML
    private Button buttonNo;

    protected SavingViewFXML() {
    }

    public static SavingViewFXML getInstance() {
        return instance == null ? instance = new SavingViewFXML() : instance;
    }

    @Override
    public void initialize(ResourceBundle resourceBundle) {
        try {
            URL fxmlUrl = OperationViewFXML.class.getResource(PathResourceFXML);
            if (fxmlUrl != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl, resourceBundle);
                fxmlLoader.setController(instance);
                fxmlLoader.load();
                bundle = resourceBundle;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        try {
            URL fxmlUrl = SavingViewFXML.class.getResource(PathResourceFXML);
            if (fxmlUrl != null) {
                FXMLLoader loader = new FXMLLoader(fxmlUrl, bundle);
                loader.setController(instance);

                Parent newRoot = loader.load();

                Scene scene = new Scene(newRoot);
                Stage newWindow = new Stage();
                newWindow.getIcons().add(new Image(PATH_LOGO_APP_IMAGE));
                newWindow.setTitle(bundle.getString("ChoiceSave.title"));
                newWindow.initModality(Modality.APPLICATION_MODAL);
                newWindow.setResizable(false);
                newWindow.setScene(scene);

                this.buttonYes.setOnAction(e -> {
                    handleYes.run();
                    handleNo.run();
                    newWindow.close();
                });

                this.buttonNo.setOnAction(e -> {
                    handleNo.run();
                    newWindow.close();
                });

                newWindow.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
