package ch.supsi.imageEditor.frontend.view.fxml.controlled;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.adapter.MenuItemAdapter;
import ch.supsi.imageEditor.frontend.controller.observer.EventOnApplication;
import ch.supsi.imageEditor.frontend.controller.observer.HandleServiceInterface;
import ch.supsi.imageEditor.frontend.model.AbstractModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class MenuBarViewFXML implements MenuBarViewFXMLInterface {
    private static final String PathResourceFXML = "/menubar.fxml";
    private static MenuBarViewFXML instance = null;
    private static ResourceBundle bundle;
    private final Map<EventType, Runnable> onEventDoActionMap;
    private HandleServiceInterface handleService;
    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu fileMenu;
    @FXML
    private Menu editMenu;
    @FXML
    private Menu helpMenu;

    @FXML
    private MenuItem openMenuItem;
    @FXML
    private Menu openRecentMenu;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem saveAsMenuItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private MenuItem quitMenuItem;
    @FXML
    private Menu languageMenu;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private MenuItem helpMenuItem;

    private MenuBarViewFXML() {
        this.onEventDoActionMap = new HashMap<>();
    }

    public static MenuBarViewFXML getInstance(ResourceBundle resourceBundle) {
        if (instance == null) {
            instance = new MenuBarViewFXML();
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

        return instance;
    }

    @Override
    public Node getNode() {
        return this.menuBar;
    }

    @Override
    public void initialize(HandleServiceInterface handleService, AbstractModel model) {
        this.handleService = handleService;
        this.fillMap();
        this.createBehaviour();
    }

    private void fillMap() {
        this.onEventDoActionMap.put(EventType.OPEN_IMAGE, this::enablePersistingButtons);
        this.onEventDoActionMap.put(EventType.CLOSE_IMAGE, this::disablePersistingButtons);
    }

    private void createBehaviour() {
        this.aboutMenuItem.setOnAction(event -> this.handleService.notify(EventOnApplication.ABOUT, new MenuItemAdapter((MenuItem) event.getSource())));
        this.helpMenuItem.setOnAction(event -> this.handleService.notify(EventOnApplication.HELP, new MenuItemAdapter((MenuItem) event.getSource())));
        this.openMenuItem.setOnAction(event -> this.handleService.notify(EventOnApplication.OPEN_IMAGE, new MenuItemAdapter((MenuItem) event.getSource())));
        this.saveMenuItem.setOnAction(event -> this.handleService.notify(EventOnApplication.SAVE_IMAGE, new MenuItemAdapter((MenuItem) event.getSource())));
        this.saveAsMenuItem.setOnAction(event -> this.handleService.notify(EventOnApplication.SAVE_IMAGE_AS, new MenuItemAdapter((MenuItem) event.getSource())));
        this.closeMenuItem.setOnAction(event -> this.handleService.notify(EventOnApplication.CLOSE_IMAGE, new MenuItemAdapter((MenuItem) event.getSource())));
        this.quitMenuItem.setOnAction(actionEvent -> Platform.exit());
    }

    @Override
    public void update(EventType eventType) {
        Runnable action = this.onEventDoActionMap.get(eventType);
        if (action != null)
            action.run();
    }

    private void enablePersistingButtons() {
        this.saveMenuItem.setDisable(false);
        this.saveAsMenuItem.setDisable(false);
        this.closeMenuItem.setDisable(false);
    }

    private void disablePersistingButtons() {
        this.saveMenuItem.setDisable(true);
        this.saveAsMenuItem.setDisable(true);
        this.closeMenuItem.setDisable(true);
    }

    @Override
    public void createSupportedLanguagesMenuItem(Set<String> supportedLanguages) {
        for (String supportedLanguage : supportedLanguages) {
            MenuItem item = new MenuItem(supportedLanguage);
            item.setId(supportedLanguage);
            item.setMnemonicParsing(false);
            item.setText(bundle.getString("MenuBar." + supportedLanguage));
            item.setOnAction(event -> this.handleService.notify(EventOnApplication.CHANGE_LANGUAGE, new MenuItemAdapter((MenuItem) event.getSource())));
            this.languageMenu.getItems().add(item);
        }
    }

    @Override
    public void createOpenRecentMenuItem(Set<String> recentFiles) {
        for (String recentFile : recentFiles) {
            MenuItem item = new MenuItem(recentFile);
            item.setId(recentFile);
            item.setMnemonicParsing(false);
            //item.setText(bundle.getString("MenuBar." + supportedLanguage));
            //item.setOnAction(event -> this.handleService.notify(EventOnApplication.CHANGE_LANGUAGE, new MenuItemAdapter((MenuItem) event.getSource())));
            this.languageMenu.getItems().add(item);
        }
    }
}
