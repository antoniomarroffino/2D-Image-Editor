package ch.supsi.imageEditor.backend.application.pipeline;

import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;

import java.util.NoSuchElementException;

public interface PipelineControllerInterface {
    void addOperationToPipeline(String name) throws OperationNotSupportedException;
    String getLastOperationAdded();
    void deletePipeline();
    void runPipeline();
}
