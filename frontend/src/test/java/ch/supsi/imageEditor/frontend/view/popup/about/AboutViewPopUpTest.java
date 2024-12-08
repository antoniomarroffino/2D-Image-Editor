package ch.supsi.imageEditor.frontend.view.popup.about;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

class AboutViewPopUpTest {
    private ResourceBundle resourceBundle;

    @BeforeAll
    public static void beforeClass() {
        // initialize javafx toolkit
        JFXPanel fxPanel = new JFXPanel();
    }

    @BeforeEach
    public void beforeEach() {
        AboutViewPopUp.instance = null;
        this.resourceBundle = ResourceBundle.getBundle("i18n.labels", Locale.forLanguageTag("it-CH"));
    }

    @Test
    public void constructor() {
        AboutViewPopUp aboutViewPopUp = new AboutViewPopUp();
        Assertions.assertNotNull(aboutViewPopUp);
    }

    @Test
    public void instance() {
        AboutViewPopUp aboutViewPopUp = AboutViewPopUp.getInstance(this.resourceBundle);
        Assertions.assertNotNull(aboutViewPopUp);
        Assertions.assertNotNull(AboutViewPopUp.instance);
    }

    @Test
    public void checkSingleton() {
        AboutViewPopUp aboutViewPopUp1 = AboutViewPopUp.getInstance(this.resourceBundle);
        AboutViewPopUp aboutViewPopUp2 = AboutViewPopUp.getInstance(this.resourceBundle);
        Assertions.assertEquals(aboutViewPopUp1, aboutViewPopUp2);
    }
}