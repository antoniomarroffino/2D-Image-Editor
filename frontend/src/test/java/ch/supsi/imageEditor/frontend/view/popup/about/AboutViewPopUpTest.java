package ch.supsi.imageEditor.frontend.view.popup.about;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.Locale;
import java.util.ResourceBundle;

@ExtendWith(ApplicationExtension.class)
class AboutViewPopUpTest {
    private ResourceBundle resourceBundle;

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