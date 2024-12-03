package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.operation.allOperations.Operation;

import java.util.List;
import java.util.Map;

public class OperationModel implements OperationModelInterface {
    protected static OperationModel instance;

    protected OperationModel() {

    }
    public static OperationModel getInstance() {
        return instance == null ? instance = new OperationModel() : instance;
    }

    @Override
    public AbstractImage executeOperations(List<String> operationsTag, AbstractImage currentImage, Map<String, Operation> availableOperationsMap) {
        for (String operationTag : operationsTag) {
            Operation operation = availableOperationsMap.get(operationTag);
            currentImage = operation.doOperation(currentImage);
        }
        return currentImage;
    }
}
