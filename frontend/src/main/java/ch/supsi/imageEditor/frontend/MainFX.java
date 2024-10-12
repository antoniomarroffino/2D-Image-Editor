package ch.supsi.imageEditor.frontend;

import ch.supsi.imageEditor.frontend.controller.AppController;
import ch.supsi.imageEditor.frontend.controller.AppEventHandler;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.AppModel;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.ControlledFxView;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.MenuBarViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.OperationViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.PipelineViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.CurrentInfoViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.ImageViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.InfobarViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.UncontrolledFxView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainFX extends Application {
    public static final String APP_TITLE = "2D Image Editor";
    //private final static String PATH_LOGO_APP_IMAGE = "/images/logoApp.png";

    private final AbstractModel appModel;
    private final ControlledFxView menuBarView;
    private final UncontrolledFxView imageView;
    private final ControlledFxView operationView;
    private final UncontrolledFxView currentInfoView;
    private final ControlledFxView pipelineView;
    private final UncontrolledFxView infoBarView;
    private final AppEventHandler appEventHandler;

    public MainFX() {
        //APP MODEL
        this.appModel = AppModel.getInstance();

        //CONTROLLERS
        this.appEventHandler = AppController.getInstance();

        //VIEWS
        this.menuBarView = MenuBarViewFXML.getInstance();
        this.imageView = ImageViewFXML.getInstance();
        this.operationView = OperationViewFXML.getInstance();
        this.currentInfoView = CurrentInfoViewFXML.getInstance();
        this.pipelineView = PipelineViewFXML.getInstance();
        this.infoBarView = InfobarViewFXML.getInstance();

        //SCAFFOLDING of M-V-C
        this.menuBarView.initialize(this.appEventHandler, this.appModel);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
// handle the main window close request
        // in real life, this event should not be dealt with here!
        // it should actually be delegated to a suitable ExitController!
        primaryStage.setOnCloseRequest(
                windowEvent -> {
                    // consume the window event (the main window would be closed otherwise no matter what)
                    windowEvent.consume();

                    // quit the app
                    // replace this hard close
                    // by delegating the work to a suitable controller
                    primaryStage.close();
                }
        );

        // SCAFFOLDING OF MAIN PANE
        BorderPane mainBorderPane = new BorderPane();
        mainBorderPane.setTop(this.menuBarView.getNode());
        mainBorderPane.setCenter(this.imageView.getNode());

        BorderPane leftBorderPane = new BorderPane();
        leftBorderPane.setTop(this.operationView.getNode());
        leftBorderPane.setCenter(this.currentInfoView.getNode());

        BorderPane rightBorderPane = new BorderPane();
        rightBorderPane.setTop(this.pipelineView.getNode());
        rightBorderPane.setCenter(this.infoBarView.getNode());

        mainBorderPane.setLeft(leftBorderPane);
        mainBorderPane.setRight(rightBorderPane);

        // SCENE
        Scene scene = new Scene(mainBorderPane);

        // STYLE
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/dark-theme.css")).toExternalForm());


        // PRIMARY STAGE
        primaryStage.setTitle(MainFX.APP_TITLE);
        //primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(PATH_LOGO_APP_IMAGE))));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.toFront();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
