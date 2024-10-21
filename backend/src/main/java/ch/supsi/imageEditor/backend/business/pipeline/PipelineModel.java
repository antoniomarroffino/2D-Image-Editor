package ch.supsi.imageEditor.backend.business.pipeline;

import ch.supsi.imageEditor.backend.application.pipeline.PipelineControllerInterface;
public class PipelineModel implements PipelineControllerInterface {
    private static PipelineModel instance;
    //private final OperationDataAccess operationDataAccess;

    private PipelineModel() {
        //this.operationDataAccess = OperationDataAccess.getInstance();
    }

    public static PipelineModel getInstance() {
        return instance == null ? instance = new PipelineModel() : instance;
    }

}
