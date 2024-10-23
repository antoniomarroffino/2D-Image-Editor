package ch.supsi.imageEditor.frontend.controller.pipeline;

import ch.supsi.imageEditor.frontend.adapter.Component;

public interface PipelineControllerInterface {
    void addOperationToPipeline(Component node);
    void deletePipeline(Component node);
    void runPipeline(Component node);
}
