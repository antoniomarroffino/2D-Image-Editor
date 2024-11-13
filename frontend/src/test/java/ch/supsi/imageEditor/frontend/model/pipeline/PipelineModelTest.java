package ch.supsi.imageEditor.frontend.model.pipeline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PipelineModelTest {
    @BeforeEach
    public void beforeEach() {
        PipelineModel.instance = null;
    }

    @Test
    public void constructor() {
        PipelineModel pipelineModel = new PipelineModel();
        Assertions.assertNotNull(pipelineModel);
        Assertions.assertNotNull(pipelineModel.getPipelineController());
    }

    @Test
    public void instance() {
        PipelineModel pipelineModel = PipelineModel.getInstance();
        Assertions.assertNotNull(pipelineModel);
        Assertions.assertNotNull(PipelineModel.instance);
        Assertions.assertNotNull(pipelineModel.getPipelineController());
    }

    @Test
    public void checkSingleton() {
        PipelineModel pipelineModel1 = PipelineModel.getInstance();
        PipelineModel pipelineModel2 = PipelineModel.getInstance();
        Assertions.assertEquals(pipelineModel1, pipelineModel2);
    }
}