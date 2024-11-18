package ch.supsi.imageEditor.frontend.controller.pipeline;

import ch.supsi.imageEditor.backend.application.observer.EventListener;
import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.persist.PersistImageModel;
import ch.supsi.imageEditor.frontend.model.pipeline.PipelineModel;
import ch.supsi.imageEditor.frontend.model.pipeline.PipelineModelInterface;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModel;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

import java.util.List;

public class PipelineController implements PipelineControllerInterface, EventListener {
    protected static PipelineController instance = null;
    private final PubSubModelInterface pubSubModel;
    private final PipelineModelInterface pipelineModel;
    private final PersistImageModel persistModel;
    private List<DataView> views;

    protected PipelineController() {
        this.pipelineModel = PipelineModel.getInstance();
        this.pubSubModel = PubSubModel.getInstance();
        this.persistModel = PersistImageModel.getInstance();
        this.pubSubModel.subscribe(EventType.ADDED_OPERATION, this);
        this.pubSubModel.subscribe(EventType.CLEAR_PIPELINE, this);
    }

    public static PipelineController getInstance() {
        return instance == null ? instance = new PipelineController() : instance;
    }

    PubSubModelInterface getPubSubModel() {
        return pubSubModel;
    }

    PipelineModelInterface getPipelineModel() {
        return pipelineModel;
    }

    PersistImageModel getPersistModel() {
        return persistModel;
    }

    @Override
    public void initialize(List<DataView> views) {
        this.views = views;
    }

    @Override
    public void deletePipeline(Component node) {
        this.pipelineModel.deletePipeline();
    }

    @Override
    public void runPipeline(Component node) {
        this.pipelineModel.runPipeline();
        this.persistModel.setAlreadySave(false);
    }

    @Override
    public void update(EventType eventType) {
        for (DataView view : this.views)
            view.update(eventType);
    }
}
