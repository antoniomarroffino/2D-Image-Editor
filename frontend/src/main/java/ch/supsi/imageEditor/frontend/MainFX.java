package ch.supsi.imageEditor.frontend;

import ch.supsi.imageEditor.frontend.controller.AppController;
import ch.supsi.imageEditor.frontend.controller.AppControllerInterface;
import ch.supsi.imageEditor.frontend.controller.image.ImageController;
import ch.supsi.imageEditor.frontend.controller.image.ImageControllerInterface;
import ch.supsi.imageEditor.frontend.controller.language.LanguageController;
import ch.supsi.imageEditor.frontend.controller.language.LanguageControllerInterface;
import ch.supsi.imageEditor.frontend.controller.observer.EventOnApplication;
import ch.supsi.imageEditor.frontend.controller.observer.HandleService;
import ch.supsi.imageEditor.frontend.controller.observer.HandleServiceInterface;
import ch.supsi.imageEditor.frontend.controller.operation.OperationController;
import ch.supsi.imageEditor.frontend.controller.operation.OperationControllerInterface;
import ch.supsi.imageEditor.frontend.controller.saving.SavingController;
import ch.supsi.imageEditor.frontend.controller.saving.SavingControllerInterface;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.AppModel;
import ch.supsi.imageEditor.frontend.model.about.AboutModel;
import ch.supsi.imageEditor.frontend.model.about.AboutModelInterface;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModel;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModelInterface;
import ch.supsi.imageEditor.frontend.model.image.ImageModel;
import ch.supsi.imageEditor.frontend.model.image.ImageModelInterface;
import ch.supsi.imageEditor.frontend.model.language.LanguageModel;
import ch.supsi.imageEditor.frontend.model.language.LanguageModelInterface;
import ch.supsi.imageEditor.frontend.model.operation.OperationModel;
import ch.supsi.imageEditor.frontend.model.operation.OperationModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.*;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.CurrentInfoViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.ImageViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.InfobarViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.UncontrolledFxView;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXMLInterface;
import ch.supsi.imageEditor.frontend.view.popup.about.AboutViewInterface;
import ch.supsi.imageEditor.frontend.view.popup.about.AboutViewPopUp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.ResourceBundle;

public class MainFX extends Application {
    public static final String APP_TITLE = "2D Image Editor";
    private static final String PATH_LOGO_APP_IMAGE = "/images/logoApp.png";

    private final AbstractModel appModel;
    private final AboutModelInterface aboutModel;
    private final LanguageModelInterface languageModel;
    private final OperationModelInterface operationModel;
    private final HandleViewModelInterface handleViewModel;
    private final ImageModelInterface imageModel;

    private final MenuBarViewFXMLInterface menuBarView;
    private final UncontrolledFxView imageView;
    private final OperationViewFXMLInterface operationView;
    private final UncontrolledFxView currentInfoView;
    private final ControlledFxView pipelineView;
    private final UncontrolledFxView infoBarView;
    private final AboutViewInterface aboutView;
    private final SavingViewFXMLInterface savingView;

    private final HandleServiceInterface handleService;
    private final AppControllerInterface appController;
    private final LanguageControllerInterface languageController;
    private final OperationControllerInterface operationController;
    private final SavingControllerInterface savingController;
    private final ImageControllerInterface imageController;

    private final ResourceBundle resourceBundle;

    public MainFX() {
        //APP MODEL
        this.appModel = AppModel.getInstance();
        this.aboutModel = AboutModel.getInstance();
        this.languageModel = LanguageModel.getInstance();
        this.operationModel = OperationModel.getInstance();
        this.handleViewModel = HandleViewModel.getInstance();
        this.imageModel = ImageModel.getInstance();

        //CONTROLLERS
        this.handleService = HandleService.getInstance();

        this.appController = AppController.getInstance();
        this.languageController = LanguageController.getInstance();
        this.operationController = OperationController.getInstance();
        this.savingController = SavingController.getInstance();
        this.imageController = ImageController.getInstance();

        this.handleService.subscribe(EventOnApplication.ABOUT, this.appController::about);
        this.handleService.subscribe(EventOnApplication.HELP, this.appController::help);
        this.handleService.subscribe(EventOnApplication.CHANGE_LANGUAGE, this.languageController::changeLanguage);
        this.handleService.subscribe(EventOnApplication.CLICK_OPERATION, this.operationController::addOperationToPipeline);
        this.handleService.subscribe(EventOnApplication.OPEN_IMAGE, this.savingController::openImage);
        this.resourceBundle = languageModel.getCurrentResourceBundle();

        //VIEWS
        this.menuBarView = MenuBarViewFXML.getInstance(this.resourceBundle);
        this.imageView = ImageViewFXML.getInstance(this.resourceBundle);
        this.operationView = OperationViewFXML.getInstance(this.resourceBundle);
        this.currentInfoView = CurrentInfoViewFXML.getInstance(this.resourceBundle);
        this.pipelineView = PipelineViewFXML.getInstance(this.resourceBundle);
        this.infoBarView = InfobarViewFXML.getInstance(this.resourceBundle);
        this.aboutView = AboutViewPopUp.getInstance(this.resourceBundle);
        this.savingView = SavingViewFXML.getInstance();

        //SCAFFOLDING of M-V-C
        this.menuBarView.initialize(this.handleService, this.appModel, this.handleViewModel);
        this.operationView.initialize(this.handleService, this.appModel, this.handleViewModel);
        this.aboutView.initialize(this.aboutModel);
        this.infoBarView.initialize((AbstractModel) this.languageModel, this.handleViewModel);
        this.imageView.initialize((AbstractModel) this.imageModel, this.handleViewModel);

        this.operationView.createSupportedOperationsButtons(this.operationModel.getSupportedOperations());
        this.menuBarView.createSupportedLanguagesMenuItem(this.languageModel.getSupportedLanguages());
        //this.menuBarView.createOpenRecentMenuItem(null);
        // this.operationView.createOperationMenuItem(null);
    }

    public static void main(String[] args) {
        launch(args);
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
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(PATH_LOGO_APP_IMAGE))));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.toFront();
        primaryStage.show();

        this.savingView.setMainStage(primaryStage);
    }
}
