package ch.supsi.imageEditor.frontend.controller.pipeline;

import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

import java.util.List;

public interface PipelineControllerInterface {
    void initialize(List<DataView> views);

    void addOperationToPipeline(Component node);

    void deletePipeline(Component node);

    void runPipeline(Component node);

}
