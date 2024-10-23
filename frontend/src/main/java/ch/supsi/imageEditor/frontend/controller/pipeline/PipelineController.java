package ch.supsi.imageEditor.frontend.controller.pipeline;

import ch.supsi.imageEditor.backend.application.observer.EventListener;
import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.pipeline.PipelineModel;
import ch.supsi.imageEditor.frontend.model.pipeline.PipelineModelInterface;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModel;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

import java.util.List;

public class PipelineController implements PipelineControllerInterface, EventListener {
    private static PipelineController instance = null;
    private final PubSubModelInterface pubSubModel;
    private final PipelineModelInterface pipelineModel;
    private List<DataView> views;


    private PipelineController() {
        this.pipelineModel = PipelineModel.getInstance();
        this.pubSubModel = PubSubModel.getInstance();
        this.pubSubModel.subscribe(EventType.ADDED_OPERATION, this);
        this.pubSubModel.subscribe(EventType.CLEAR_PIPELINE, this);
    }

    public static PipelineController getInstance() {
        return instance == null ? instance = new PipelineController() : instance;
    }

    @Override
    public void initialize(List<DataView> views) {
        this.views = views;
    }

    @Override
    public void addOperationToPipeline(Component node) {
        System.out.println(node.getId());
    }

    @Override
    public void deletePipeline(Component node) { //TODO: controllare il Component
        this.pipelineModel.deletePipeline();
    }

    @Override
    public void runPipeline(Component node) { //TODO: controllare il Component
        this.pipelineModel.runPipeline();
    }


    @Override
    public void update(EventType eventType) {
        for (DataView view : this.views)
            view.update(eventType);
    }
}
