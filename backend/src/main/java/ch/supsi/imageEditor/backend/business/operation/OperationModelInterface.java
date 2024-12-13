package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.operation.allOperations.Operation;

import java.util.List;
import java.util.Map;

public interface OperationModelInterface {
    AbstractImage executeOperations(List<String> operationsTag, AbstractImage currentImage, Map<String, Operation> availableOperationsMap);
}
