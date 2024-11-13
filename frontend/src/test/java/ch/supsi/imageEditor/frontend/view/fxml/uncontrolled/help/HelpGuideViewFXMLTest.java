package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.help;

import ch.supsi.imageEditor.frontend.view.fxml.InitializePlatform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

class HelpGuideViewFXMLTest extends InitializePlatform {
    private ResourceBundle resourceBundle;

    @BeforeAll
    static void initJavaFX() {
        InitializePlatform.initializedPlatform();
    }

    @BeforeEach
    public void beforeEach() {
        HelpGuideViewFXML.instance = null;
        this.resourceBundle = ResourceBundle.getBundle("i18n.labels", Locale.forLanguageTag("it-CH"));
    }

    @Test
    public void constructor() {
        HelpGuideViewFXML helpGuideViewFXML = new HelpGuideViewFXML();
        Assertions.assertNotNull(helpGuideViewFXML);
    }

    @Test
    public void instance() {
        HelpGuideViewFXML helpGuideViewFXML = HelpGuideViewFXML.getInstance(this.resourceBundle);
        Assertions.assertNotNull(helpGuideViewFXML);
        Assertions.assertNotNull(HelpGuideViewFXML.instance);
    }

    @Test
    public void checkSingleton() {
        HelpGuideViewFXML helpGuideViewFXML1 = HelpGuideViewFXML.getInstance(this.resourceBundle);
        HelpGuideViewFXML helpGuideViewFXML2 = HelpGuideViewFXML.getInstance(this.resourceBundle);
        Assertions.assertEquals(helpGuideViewFXML1, helpGuideViewFXML2);
    }
}