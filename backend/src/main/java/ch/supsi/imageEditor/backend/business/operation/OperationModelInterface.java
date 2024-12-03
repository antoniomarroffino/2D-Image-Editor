package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.operation.allOperations.Operation;
import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OperationModelInterface {
    AbstractImage executeOperations(List<String> operationsTag, AbstractImage currentImage, Map<String, Operation> availableOperationsMap);
}
