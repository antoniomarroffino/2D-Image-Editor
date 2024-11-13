package ch.supsi.imageEditor.frontend.model.pipeline;

import ch.supsi.imageEditor.backend.application.pipeline.PipelineController;
import ch.supsi.imageEditor.backend.application.pipeline.PipelineControllerInterface;
import ch.supsi.imageEditor.frontend.model.AbstractModel;

public class PipelineModel extends AbstractModel implements PipelineModelInterface {
    protected static PipelineModel instance;
    private final PipelineControllerInterface pipelineController;

    protected PipelineModel() {
        this.pipelineController = PipelineController.getInstance();
    }

    public static PipelineModel getInstance() {
        return instance == null ? instance = new PipelineModel() : instance;
    }

    PipelineControllerInterface getPipelineController() {
        return this.pipelineController;
    }

    @Override
    public String getAddedOperation() {
        return this.pipelineController.getLastOperationAdded();
    }

    @Override
    public void deletePipeline() {
        this.pipelineController.deletePipeline();
    }

    @Override
    public void runPipeline() {
        this.pipelineController.runPipeline();
    }
}
