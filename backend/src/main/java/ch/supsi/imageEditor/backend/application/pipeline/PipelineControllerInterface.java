package ch.supsi.imageEditor.backend.application.pipeline;

import java.util.NoSuchElementException;

public interface PipelineControllerInterface {
    void addOperationToPipeline(String name);
    String getLastOperationAdded();
    void deletePipeline();
    void runPipeline();
}
