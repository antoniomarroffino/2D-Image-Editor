package ch.supsi.imageEditor.frontend.view.fxml.controlled.pipeline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.Locale;
import java.util.ResourceBundle;

@ExtendWith(ApplicationExtension.class)
class PipelineViewFXMLTest {
    private ResourceBundle resourceBundle;

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