package ch.supsi.imageEditor.frontend.controller.pipeline;

import ch.supsi.imageEditor.backend.application.observer.EventListener;
import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModel;
import ch.supsi.imageEditor.frontend.model.handleViewService.HandleViewModelInterface;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModel;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

public class PipelineController implements PipelineControllerInterface, EventListener {
    private static PipelineController instance = null;
    private final HandleViewModelInterface handleViewModel;
    private final PubSubModelInterface pubSubModel;
    //private final PipelineModelInterface pipelineModel;


    private PipelineController() {
        //this.pipelineModel = PipelineModel.getInstance();
        this.handleViewModel = HandleViewModel.getInstance();
        this.pubSubModel = PubSubModel.getInstance();
        this.pubSubModel.subscribe(EventType.ADDED_OPERATION, this);
    }

    public static PipelineController getInstance() {
        return instance == null ? instance = new PipelineController() : instance;
    }

    @Override
    public void addOperationToPipeline(Component node) {
        System.out.println(node.getId());
        //this.pipelineModel.addOperationToPipeline(node.getId());
    }

    @Override
    public void update(EventType eventType) {
        for (DataView view : this.handleViewModel.getViewsOnEventType(eventType))
            view.update(eventType);
    }
}
