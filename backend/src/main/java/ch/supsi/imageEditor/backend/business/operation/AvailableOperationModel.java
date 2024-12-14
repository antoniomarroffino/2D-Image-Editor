package ch.supsi.imageEditor.backend.business.operation;

import ch.supsi.imageEditor.backend.business.operation.allOperations.Operation;
import ch.supsi.imageEditor.backend.dataaccess.operation.OperationDataAccess;
import ch.supsi.imageEditor.backend.dataaccess.operation.OperationDataAccessInterface;
import ch.supsi.imageEditor.backend.exception.OperationNotSupportedException;
import com.google.common.collect.ImmutableMap;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class AvailableOperationModel implements AvailableOperationModelInterface {
    protected static AvailableOperationModel instance = null;

    private final OperationDataAccessInterface operationDataAccess;
    private final Set<String> supportedOperations;
    private final Properties operationProperties;
    private final Map<String, Operation> operationsMap;

    protected AvailableOperationModel() {
        this.operationDataAccess = OperationDataAccess.getInstance();
        this.supportedOperations = this.operationDataAccess.getOperationsTag();
        this.operationProperties = this.operationDataAccess.getOperationProperties();
        this.operationsMap = this.loadOperationsMap();

    }

    public static AvailableOperationModel getInstance() {
        return instance == null ? instance = new AvailableOperationModel() : instance;
    }

    private Map<String, Operation> loadOperationsMap() {
        Map<String, Operation> operationMap = new HashMap<>();
        for (String operationTag : this.operationProperties.stringPropertyNames()) {
            String operationClassName = this.operationProperties.getProperty(operationTag);
            try {
                Class<?> operationClass = Class.forName(operationClassName);
                Operation operation = (Operation) operationClass.getConstructor().newInstance();
                operationMap.put(operationTag, operation);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException ignored) {
                ;
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
    public Map<String, Operation> getAvailableOperations() {
        return ImmutableMap.copyOf(this.operationsMap);
    }

    @Override
    public Set<String> getOperationsTag() {
        return this.orderOperations(this.supportedOperations);
    }
}
