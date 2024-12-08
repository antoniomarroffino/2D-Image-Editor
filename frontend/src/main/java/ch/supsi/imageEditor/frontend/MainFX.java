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
import ch.supsi.imageEditor.frontend.controller.persisting.PersistImageController;
import ch.supsi.imageEditor.frontend.controller.persisting.PersistImageControllerInterface;
import ch.supsi.imageEditor.frontend.controller.pipeline.PipelineController;
import ch.supsi.imageEditor.frontend.controller.pipeline.PipelineControllerInterface;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import ch.supsi.imageEditor.frontend.model.AppModel;
import ch.supsi.imageEditor.frontend.model.about.AboutModel;
import ch.supsi.imageEditor.frontend.model.about.AboutModelInterface;
import ch.supsi.imageEditor.frontend.model.exit.ExitModel;
import ch.supsi.imageEditor.frontend.model.exit.ExitModelInterface;
import ch.supsi.imageEditor.frontend.model.image.ImageModel;
import ch.supsi.imageEditor.frontend.model.image.ImageModelInterface;
import ch.supsi.imageEditor.frontend.model.language.LanguageModel;
import ch.supsi.imageEditor.frontend.model.language.LanguageModelInterface;
import ch.supsi.imageEditor.frontend.model.operation.OperationModel;
import ch.supsi.imageEditor.frontend.model.operation.OperationModelInterface;
import ch.supsi.imageEditor.frontend.model.persist.PersistImageModel;
import ch.supsi.imageEditor.frontend.model.persist.PersistImageModelInterface;
import ch.supsi.imageEditor.frontend.model.pipeline.PipelineModel;
import ch.supsi.imageEditor.frontend.model.pipeline.PipelineModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.ControlledFxView;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.menubar.MenuBarViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.menubar.MenuBarViewFXMLInterface;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.operation.OperationViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.operation.OperationViewFXMLInterface;
import ch.supsi.imageEditor.frontend.view.fxml.controlled.pipeline.PipelineViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.UncontrolledFxView;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.currentInfo.CurrentInfoViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.image.ImageViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.infobar.InfobarViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXMLInterface;
import ch.supsi.imageEditor.frontend.view.popup.about.AboutViewInterface;
import ch.supsi.imageEditor.frontend.view.popup.about.AboutViewPopUp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainFX extends Application {
    public static final String APP_TITLE = "2D Image Editor";
    private static final String PATH_LOGO_APP_IMAGE = "/images/logoApp.png";

    private final AbstractModel appModel;
    private final AboutModelInterface aboutModel;
    private final LanguageModelInterface languageModel;
    private final OperationModelInterface operationModel;
    private final PipelineModelInterface pipelineModel;
    private final ImageModelInterface imageModel;
    private final PersistImageModelInterface persistImageModel;
    private final ExitModelInterface exitModel;

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
    private final PipelineControllerInterface pipelineController;
    private final PersistImageControllerInterface persistImageController;
    private final ImageControllerInterface imageController;

    private final ResourceBundle resourceBundle;

    public MainFX() {
        //APP MODEL
        this.appModel = AppModel.getInstance();
        this.aboutModel = AboutModel.getInstance();
        this.languageModel = LanguageModel.getInstance();
        this.operationModel = OperationModel.getInstance();
        this.pipelineModel = PipelineModel.getInstance();
        this.imageModel = ImageModel.getInstance();
        this.persistImageModel = PersistImageModel.getInstance();
        this.exitModel = ExitModel.getInstance();

        //CONTROLLERS
        this.handleService = HandleService.getInstance();
        this.appController = AppController.getInstance();
        this.languageController = LanguageController.getInstance();
        this.operationController = OperationController.getInstance();
        this.pipelineController = PipelineController.getInstance();
        this.persistImageController = PersistImageController.getInstance();
        this.imageController = ImageController.getInstance();

        this.handleService.subscribe(EventOnApplication.ABOUT, this.appController::about);
        this.handleService.subscribe(EventOnApplication.HELP, this.appController::help);
        this.handleService.subscribe(EventOnApplication.CHANGE_LANGUAGE, this.languageController::changeLanguage);
        this.handleService.subscribe(EventOnApplication.RUN_PIPELINE, this.pipelineController::runPipeline);
        this.handleService.subscribe(EventOnApplication.DELETE_PIPELINE, this.pipelineController::deletePipeline);
        this.handleService.subscribe(EventOnApplication.CLICK_OPERATION, this.operationController::addOperationToPipeline);
        this.handleService.subscribe(EventOnApplication.OPEN_IMAGE, this.persistImageController::requestSaveBeforeOpen);
        this.handleService.subscribe(EventOnApplication.OPEN_RECENT, this.persistImageController::requestSaveBeforeOpenRecent);
        this.handleService.subscribe(EventOnApplication.SAVE_IMAGE, this.persistImageController::saveImage);
        this.handleService.subscribe(EventOnApplication.SAVE_IMAGE_AS, this.persistImageController::saveImageAs);
        this.handleService.subscribe(EventOnApplication.CLOSE_IMAGE, this.persistImageController::requestSaveBeforeClose);
        this.handleService.subscribe(EventOnApplication.QUIT_APPLICATION, this.persistImageController::requestSaveBeforeQuit);

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
        savingView.initialize(this.resourceBundle);

        //SCAFFOLDING of M-V-C
        this.menuBarView.initialize(this.handleService, (AbstractModel) this.persistImageModel);
        this.operationView.initialize(this.handleService, (AbstractModel) this.operationModel);
        this.currentInfoView.initialize((AbstractModel) this.persistImageModel);
        this.aboutView.initialize(this.aboutModel);
        this.infoBarView.initialize((AbstractModel) this.languageModel);
        this.pipelineView.initialize(this.handleService, (AbstractModel) this.pipelineModel);
        this.infoBarView.initialize((AbstractModel) this.languageModel);
        this.imageView.initialize((AbstractModel) this.imageModel);

        List<DataView> listOfViews = List.of(this.menuBarView, this.imageView,
                this.operationView, this.currentInfoView,
                this.pipelineView, this.infoBarView);

        this.languageController.initialize(listOfViews);
        this.imageController.initialize(listOfViews);
        this.pipelineController.initialize(listOfViews);
        this.persistImageController.initialize(listOfViews);

        this.operationView.createSupportedOperationsButtons(this.operationModel.getSupportedOperations());
        this.menuBarView.createSupportedLanguagesMenuItem(this.languageModel.getSupportedLanguages());
        this.menuBarView.createOpenRecentMenuItem(this.persistImageModel.getRecentFiles());
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setOnCloseRequest(
                windowEvent -> {
                    windowEvent.consume();

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
        this.exitModel.setStage(primaryStage);
    }
}
