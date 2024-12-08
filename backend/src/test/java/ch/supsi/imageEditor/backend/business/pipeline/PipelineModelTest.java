package ch.supsi.imageEditor.backend.business.pipeline;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PipelineModelTest {

    private PipelineModel pipelineModel;

    @BeforeEach
    public void beforeEach() {
        PipelineModel.instance = null;
    }

    @Test
    public void constructor() {
        PipelineModel pipelineModel = new PipelineModel();
        Assertions.assertNotNull(pipelineModel);
        Assertions.assertNotNull(pipelineModel.getPipeline());
    }

    @Test
    public void instance() {
        PipelineModel pipelineModel = PipelineModel.getInstance();
        Assertions.assertNotNull(pipelineModel);
        Assertions.assertNotNull(PipelineModel.instance);
        Assertions.assertNotNull(pipelineModel.getPipeline());
    }

    @Test
    public void checkSingleton() {
        PipelineModel pipelineModel1 = PipelineModel.getInstance();
        PipelineModel pipelineModel2 = PipelineModel.getInstance();
        Assertions.assertEquals(pipelineModel1, pipelineModel2);
    }

    @Test
    public void addOperationToPipelineTest() {
        this.pipelineModel = PipelineModel.getInstance();
        this.pipelineModel.addOperationToPipeline("rotate-90-left");
        this.pipelineModel.addOperationToPipeline("rotate-90-right");
        Assertions.assertEquals(2, pipelineModel.getPipeline().size());
    }

    @Test
    public void getLastOperationAddedTest() {
        this.pipelineModel = PipelineModel.getInstance();
        this.pipelineModel.addOperationToPipeline("rotate-90-left");
        this.pipelineModel.addOperationToPipeline("rotate-90-right");
        String operationName = this.pipelineModel.getLastOperationAdded();
        Assertions.assertEquals("rotate-90-right", operationName);

    }

    @Test
    public void cleanPipelineTest() {
        this.pipelineModel = PipelineModel.getInstance();
        this.pipelineModel.addOperationToPipeline("rotate-90-left");
        this.pipelineModel.addOperationToPipeline("rotate-90-right");
        this.pipelineModel.cleanPipeline();
        String operationName = this.pipelineModel.getLastOperationAdded();
        Assertions.assertEquals(0, pipelineModel.getPipeline().size());
        Assertions.assertNull(operationName);
    }
}