package ch.supsi.imageEditor.frontend.model.pipeline;

import ch.supsi.imageEditor.backend.application.pipeline.PipelineController;
import ch.supsi.imageEditor.backend.application.pipeline.PipelineControllerInterface;
import ch.supsi.imageEditor.frontend.model.AbstractModel;

public class PipelineModel extends AbstractModel implements PipelineModelInterface {
    private static PipelineModel instance;
    private final PipelineControllerInterface pipelineController;


    private PipelineModel() {
        this.pipelineController = PipelineController.getInstance();
    }

    public static PipelineModel getInstance() {
        return instance == null ? instance = new PipelineModel() : instance;
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
