package ch.supsi.imageEditor.frontend.view.popup.about;


import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AboutViewPopUp implements AboutViewInterface{
    private static AboutViewPopUp instance = null;
    private final Properties buildProperties;
    private final static String PATH_BUILD_PROPERTIES = "/build.properties";

    private AboutViewPopUp(){
        this.buildProperties = loadBuildProperties();
    }

    public static AboutViewPopUp getInstance(){
        return instance == null? instance = new AboutViewPopUp() : instance;
    }

    @Override
    public void showAboutInformation() {
        Alert infoView = getAlertOfInfo();
        Stage stage = (Stage) infoView.getDialogPane().getScene().getWindow();  //Set icon of page
        infoView.showAndWait();
    }

    private Properties loadBuildProperties(){
        Properties properties = new Properties();
        try{
            InputStream inputStream = this.getClass().getResourceAsStream(PATH_BUILD_PROPERTIES);
            properties.load(inputStream);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return properties;
    }

    private Alert getAlertOfInfo(){
        Alert infoView = new Alert(Alert.AlertType.INFORMATION);
        infoView.setTitle("2D Image Editor - About");
        infoView.setHeaderText("Version and ArtifactID of: \n" + "2D Image Editor");
        infoView.setContentText(getVersion() + "\n" + getProjectName() + "\n" + getBuiltDate() + "\n" + getDevelopersName());
        return infoView;
    }

    private String getVersion() {
        return "Version: " + buildProperties.getProperty("build.version");
    }

    private String getProjectName() {
        return "Name: " + buildProperties.getProperty("build.name");
    }

    private String getDevelopersName() {
        return "Developers: " + buildProperties.getProperty("build.devs");

    }

    private String getBuiltDate() {
        return "Build Date: " + buildProperties.getProperty("build.timestamp");
    }
}
