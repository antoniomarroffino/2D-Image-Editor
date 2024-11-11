package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.operation.allOperations.Operation;
import ch.supsi.imageEditor.backend.dataaccess.operation.OperationDataAccess;
import ch.supsi.imageEditor.backend.dataaccess.operation.OperationDataAccessInterface;
import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;

import java.util.*;
import java.util.stream.Collectors;

public class OperationModel implements OperationModelInterface {
    protected static OperationModel instance;

    private final OperationDataAccessInterface operationDataAccess;
    private final Set<String> supportedOperations;
    private final Properties operationProperties;
    private final Map<String, Operation> operationsMap;

    protected OperationModel() {
        this.operationDataAccess = OperationDataAccess.getInstance();
        this.supportedOperations = this.operationDataAccess.getOperationsTag();
        this.operationProperties = this.operationDataAccess.getOperationProperties();
        this.operationsMap = this.loadOperationsMap();
    }

    public static OperationModel getInstance() {
        return instance == null ? instance = new OperationModel() : instance;
    }

    OperationDataAccessInterface getOperationDataAccess() {
        return this.operationDataAccess;
    }

    Set<String> getSupportedOperations() {
        return this.supportedOperations;
    }

    Properties getOperationProperties() {
        return this.operationProperties;
    }

    Map<String, Operation> getOperationsMap() {
        return this.operationsMap;
    }

    private Map<String, Operation> loadOperationsMap() {
        Map<String, Operation> operationMap = new HashMap<>();
        for (String operationTag : this.operationProperties.stringPropertyNames()) {
            String operationClassName = this.operationProperties.getProperty(operationTag);
            try {
                Class<?> operationClass = Class.forName(operationClassName);
                Operation operation = (Operation) operationClass.getConstructor().newInstance();
                operationMap.put(operationTag, operation);
            } catch (Exception e) {
                throw new RuntimeException("Error during load of operation: " + operationTag);
            }
        }
        return operationMap;
    }

    private Set<String> orderOperations(Set<String> operations) {
        return operations.stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public void checkOperationExists(String operation) throws OperationNotSupportedException {
        if (!this.supportedOperations.contains(operation))
            throw new OperationNotSupportedException("Operation " + operation + " is not supported");
    }

    @Override
    public Set<String> getOperationsTag() {
        return this.orderOperations(this.supportedOperations);
    }

    @Override
    public AbstractImage executeOperations(List<String> operationsTag, AbstractImage currentImage) { //TODO: fare algoritmo operazioni & chiedere al prof se salavare ogni volta in locale abstract image
        for (String operationTag : operationsTag) {
            Operation operation = this.operationsMap.get(operationTag);
            if (operation != null)
                currentImage = operation.doOperation(currentImage);
        }
        return currentImage;
    }
}
