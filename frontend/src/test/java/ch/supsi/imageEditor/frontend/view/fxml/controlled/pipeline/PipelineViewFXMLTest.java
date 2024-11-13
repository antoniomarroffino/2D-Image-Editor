package ch.supsi.imageEditor.frontend.view.fxml.controlled.pipeline;

import ch.supsi.imageEditor.frontend.view.fxml.InitializePlatform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

class PipelineViewFXMLTest extends InitializePlatform {
    private ResourceBundle resourceBundle;

    @BeforeAll
    static void initJavaFX() {
        InitializePlatform.initializedPlatform();
    }

    @BeforeEach
    public void beforeEach() {
        PipelineViewFXML.instance = null;
        this.resourceBundle = ResourceBundle.getBundle("i18n.labels", Locale.forLanguageTag("it-CH"));
    }

    @Test
    public void constructor() {
        PipelineViewFXML pipelineViewFXML = new PipelineViewFXML();
        Assertions.assertNotNull(pipelineViewFXML);
        Assertions.assertNotNull(pipelineViewFXML.getOnEventDoActionMap());
        Assertions.assertEquals(0, pipelineViewFXML.getCntOperationInPipeline());
    }

    @Test
    public void instance() {
        PipelineViewFXML pipelineViewFXML = PipelineViewFXML.getInstance(this.resourceBundle);
        Assertions.assertNotNull(pipelineViewFXML);
        Assertions.assertNotNull(PipelineViewFXML.instance);
        Assertions.assertNotNull(pipelineViewFXML.getOnEventDoActionMap());
        Assertions.assertEquals(0, pipelineViewFXML.getCntOperationInPipeline());
    }

    @Test
    public void checkSingleton() {
        PipelineViewFXML pipelineViewFXML1 = PipelineViewFXML.getInstance(this.resourceBundle);
        PipelineViewFXML pipelineViewFXML2 = PipelineViewFXML.getInstance(this.resourceBundle);
        Assertions.assertEquals(pipelineViewFXML1, pipelineViewFXML2);
    }
}