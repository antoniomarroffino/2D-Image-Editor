package ch.supsi.imageEditor.frontend.view.fxml.controlled.operation;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

class OperationViewFXMLTest {
    private ResourceBundle resourceBundle;

    @BeforeAll
    public static void beforeClass() {
        // initialize javafx toolkit
        JFXPanel fxPanel = new JFXPanel();
    }

    @BeforeEach
    public void beforeEach() {
        OperationViewFXML.instance = null;
        this.resourceBundle = ResourceBundle.getBundle("i18n.labels", Locale.forLanguageTag("it-CH"));
    }

    @Test
    public void constructor() {
        OperationViewFXML operationViewFXML = new OperationViewFXML();
        Assertions.assertNotNull(operationViewFXML);
        Assertions.assertNotNull(operationViewFXML.getOnEventDoActionMap());
    }

    @Test
    public void instance() {
        OperationViewFXML operationViewFXML = OperationViewFXML.getInstance(this.resourceBundle);
        Assertions.assertNotNull(operationViewFXML);
        Assertions.assertNotNull(OperationViewFXML.instance);
        Assertions.assertNotNull(operationViewFXML.getOnEventDoActionMap());
    }

    @Test
    public void checkSingleton() {
        OperationViewFXML operationViewFXML1 = OperationViewFXML.getInstance(this.resourceBundle);
        OperationViewFXML operationViewFXML2 = OperationViewFXML.getInstance(this.resourceBundle);
        Assertions.assertEquals(operationViewFXML1, operationViewFXML2);
    }
}