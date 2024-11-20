package ch.supsi.imageEditor.frontend.view.fxml.controlled.menubar;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

class MenuBarViewFXMLTest {
    private ResourceBundle resourceBundle;
    private MenuBarViewFXML menuBarViewFXML;

    @BeforeAll
    public static void beforeClass() {
        // initialize javafx toolkit
        JFXPanel fxPanel = new JFXPanel();
    }

    @BeforeEach
    public void beforeEach() {
        MenuBarViewFXML.instance = null;
        this.resourceBundle = ResourceBundle.getBundle("i18n.labels", Locale.forLanguageTag("it-CH"));
    }

    @Test
    public void constructor() {
        this.menuBarViewFXML = new MenuBarViewFXML();
        Assertions.assertNotNull(this.menuBarViewFXML);
        Assertions.assertNotNull(this.menuBarViewFXML.getOnEventDoActionMap());
    }

    @Test
    public void instance() {
        menuBarViewFXML = MenuBarViewFXML.getInstance(this.resourceBundle);
        Assertions.assertNotNull(this.menuBarViewFXML);
        Assertions.assertNotNull(MenuBarViewFXML.instance);
        Assertions.assertNotNull(this.menuBarViewFXML.getOnEventDoActionMap());
    }

    @Test
    public void checkSingleton() {
        MenuBarViewFXML menuBarViewFXML1 = MenuBarViewFXML.getInstance(this.resourceBundle);
        MenuBarViewFXML menuBarViewFXML2 = MenuBarViewFXML.getInstance(this.resourceBundle);
        Assertions.assertEquals(menuBarViewFXML1, menuBarViewFXML2);
    }
}