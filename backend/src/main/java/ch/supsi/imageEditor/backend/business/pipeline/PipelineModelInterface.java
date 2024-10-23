package ch.supsi.imageEditor.backend.business.pipeline;

public interface PipelineModelInterface {
    void addOperationToPipeline(String name);
    String getLastOperationAdded();
    void cleanPipeline();
}
