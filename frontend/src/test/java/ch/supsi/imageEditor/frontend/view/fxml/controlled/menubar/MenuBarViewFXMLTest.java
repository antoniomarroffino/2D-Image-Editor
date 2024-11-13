package ch.supsi.imageEditor.frontend.view.fxml.controlled.menubar;

import ch.supsi.imageEditor.frontend.view.fxml.InitializePlatform;
import javafx.application.Platform;
import org.junit.jupiter.api.*;

import java.util.Locale;
import java.util.ResourceBundle;

class MenuBarViewFXMLTest extends InitializePlatform {
    private ResourceBundle resourceBundle;

    @BeforeAll
    static void initJavaFX() {
        InitializePlatform.initializedPlatform();
    }

    @AfterAll
    static void destroyJavaFX() {
        Platform.exit();
    }

    @BeforeEach
    public void beforeEach() {
        MenuBarViewFXML.instance = null;
        this.resourceBundle = ResourceBundle.getBundle("i18n.labels", Locale.forLanguageTag("it-CH"));
    }

    @Test
    public void constructor() {
        MenuBarViewFXML menuBarViewFXML = new MenuBarViewFXML();
        Assertions.assertNotNull(menuBarViewFXML);
        Assertions.assertNotNull(menuBarViewFXML.getOnEventDoActionMap());
    }

    @Test
    public void instance() {
        MenuBarViewFXML menuBarViewFXML = MenuBarViewFXML.getInstance(this.resourceBundle);
        Assertions.assertNotNull(menuBarViewFXML);
        Assertions.assertNotNull(MenuBarViewFXML.instance);
        Assertions.assertNotNull(menuBarViewFXML.getOnEventDoActionMap());
    }

    @Test
    public void checkSingleton() {
        MenuBarViewFXML menuBarViewFXML1 = MenuBarViewFXML.getInstance(this.resourceBundle);
        MenuBarViewFXML menuBarViewFXML2 = MenuBarViewFXML.getInstance(this.resourceBundle);
        Assertions.assertEquals(menuBarViewFXML1, menuBarViewFXML2);
    }
}