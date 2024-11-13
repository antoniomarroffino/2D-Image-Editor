package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.currentInfo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.Locale;
import java.util.ResourceBundle;

@ExtendWith(ApplicationExtension.class)
class CurrentInfoViewFXMLTest {
    private ResourceBundle resourceBundle;

    @BeforeEach
    public void beforeEach() {
        CurrentInfoViewFXML.instance = null;
        this.resourceBundle = ResourceBundle.getBundle("i18n.labels", Locale.forLanguageTag("it-CH"));
    }

    @Test
    public void constructor() {
        CurrentInfoViewFXML currentInfoViewFXML = new CurrentInfoViewFXML();
        Assertions.assertNotNull(currentInfoViewFXML);
        Assertions.assertNotNull(currentInfoViewFXML.getOnEventDoActionMap());
        Assertions.assertNotNull(currentInfoViewFXML.getSdf());
    }

    @Test
    public void instance() {
        CurrentInfoViewFXML currentInfoViewFXML = CurrentInfoViewFXML.getInstance(this.resourceBundle);
        Assertions.assertNotNull(currentInfoViewFXML);
        Assertions.assertNotNull(CurrentInfoViewFXML.instance);
        Assertions.assertNotNull(currentInfoViewFXML.getOnEventDoActionMap());
        Assertions.assertNotNull(currentInfoViewFXML.getSdf());
    }

    @Test
    public void checkSingleton() {
        CurrentInfoViewFXML currentInfoViewFXML1 = CurrentInfoViewFXML.getInstance(this.resourceBundle);
        CurrentInfoViewFXML currentInfoViewFXML2 = CurrentInfoViewFXML.getInstance(this.resourceBundle);
        Assertions.assertEquals(currentInfoViewFXML1, currentInfoViewFXML2);
    }
}