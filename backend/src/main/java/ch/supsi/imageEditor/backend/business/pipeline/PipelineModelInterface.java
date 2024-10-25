package ch.supsi.imageEditor.backend.business.pipeline;

import java.util.List;

public interface PipelineModelInterface {
    void addOperationToPipeline(String name);
    String getLastOperationAdded();
    void cleanPipeline();
    public List<String> getPipeline();
}
