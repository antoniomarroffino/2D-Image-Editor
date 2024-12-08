package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.help;

import ch.supsi.imageEditor.frontend.view.fxml.controlled.operation.OperationViewFXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelpGuideViewFXML implements HelpGuideViewFXMLInterface {
    private static final String PathResourceFXML = "/helpguide.fxml";
    private final static String PATH_LOGO_APP_IMAGE = "/images/logoApp.png";
    protected static HelpGuideViewFXML instance = null;
    private static ResourceBundle bundle;

    @FXML
    private AnchorPane helpGuideAnchorPane;

    @FXML
    private Button buttonClose;

    protected HelpGuideViewFXML() {
    }

    public static HelpGuideViewFXML getInstance(ResourceBundle resourceBundle) {
        if (instance == null) {
            instance = new HelpGuideViewFXML();
            try {
                URL fxmlUrl = OperationViewFXML.class.getResource(PathResourceFXML);
                if (fxmlUrl != null) {
                    bundle = resourceBundle;
                    FXMLLoader loader = new FXMLLoader(fxmlUrl, bundle);
                    loader.setController(instance);
                    loader.load();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    @Override
    public void showHelpInfo() {
        try {
            URL fxmlUrl = OperationViewFXML.class.getResource(PathResourceFXML);
            if (fxmlUrl != null) {
                FXMLLoader loader = new FXMLLoader(fxmlUrl, bundle);
                loader.setController(instance);
                Parent newRoot = loader.load();

                Scene scene = new Scene(newRoot);
                Stage newWindow = new Stage();
                newWindow.getIcons().add(new Image(PATH_LOGO_APP_IMAGE));
                newWindow.setTitle("Help guide");
                newWindow.initModality(Modality.APPLICATION_MODAL);
                newWindow.setResizable(false);
                newWindow.setScene(scene);

                this.buttonClose.setOnAction(event -> newWindow.close());

                newWindow.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
