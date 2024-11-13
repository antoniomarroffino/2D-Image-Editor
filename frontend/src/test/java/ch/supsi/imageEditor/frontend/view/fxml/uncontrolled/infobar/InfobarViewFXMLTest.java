package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.infobar;

import ch.supsi.imageEditor.frontend.view.fxml.InitializePlatform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

class InfobarViewFXMLTest extends InitializePlatform {
    private ResourceBundle resourceBundle;

    @BeforeAll
    static void initJavaFX() {
        InitializePlatform.initializedPlatform();
    }

    @BeforeEach
    public void beforeEach() {
        InfobarViewFXML.instance = null;
        this.resourceBundle = ResourceBundle.getBundle("i18n.labels", Locale.forLanguageTag("it-CH"));
    }

    @Test
    public void constructor() {
        InfobarViewFXML infobarViewFXML = new InfobarViewFXML();
        Assertions.assertNotNull(infobarViewFXML);
    }

    @Test
    public void instance() {
        InfobarViewFXML infobarViewFXML = InfobarViewFXML.getInstance(this.resourceBundle);
        Assertions.assertNotNull(infobarViewFXML);
        Assertions.assertNotNull(InfobarViewFXML.instance);
    }

    @Test
    public void checkSingleton() {
        InfobarViewFXML infobarViewFXML1 = InfobarViewFXML.getInstance(this.resourceBundle);
        InfobarViewFXML infobarViewFXML2 = InfobarViewFXML.getInstance(this.resourceBundle);
        Assertions.assertEquals(infobarViewFXML1, infobarViewFXML2);
    }
}