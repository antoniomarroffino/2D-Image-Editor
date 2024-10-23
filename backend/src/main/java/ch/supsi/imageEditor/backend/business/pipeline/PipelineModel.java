package ch.supsi.imageEditor.backend.business.pipeline;

import ch.supsi.imageEditor.backend.application.pipeline.PipelineControllerInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PipelineModel implements PipelineControllerInterface {
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
    public String getLastOperationAdded(){
        if(!this.pipeline.isEmpty()){
            return this.pipeline.get(this.pipeline.size() - 1);
        }
        return null;
    }
}
