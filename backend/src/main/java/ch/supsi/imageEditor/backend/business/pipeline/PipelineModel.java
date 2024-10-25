package ch.supsi.imageEditor.backend.business.pipeline;

import java.util.ArrayList;
import java.util.List;

public class PipelineModel implements PipelineModelInterface {
    private static PipelineModel instance;
    private List<String> pipeline;

    private PipelineModel() {
        this.pipeline = new ArrayList<>();
    }

    public static PipelineModel getInstance() {
        return instance == null ? instance = new PipelineModel() : instance;
    }

    @Override
    public void addOperationToPipeline(String name) {
        this.pipeline.add(name);
    }

    @Override
    public String getLastOperationAdded() {
        if (!this.pipeline.isEmpty()) {
            return this.pipeline.get(this.pipeline.size() - 1);
        }
        return null;
    }

    @Override
    public void cleanPipeline() {
        this.pipeline.clear();
    }

    @Override
    public List<String> getPipeline() {
        return this.pipeline;
    }
}
