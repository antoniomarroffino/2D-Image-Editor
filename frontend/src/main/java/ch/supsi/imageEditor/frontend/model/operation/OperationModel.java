package ch.supsi.imageEditor.frontend.model.operation;

import ch.supsi.imageEditor.backend.application.language.LanguageController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OperationModel implements OperationModelInterface{
    private static OperationModel instance;
    //private final Map<String, String> supportedOperationsKeyTag;
    private final Set<String> operations = new HashSet<>(Arrays.asList("rotazione", "traslazione", "capovolgimento"));


    private OperationModel() {
        //this.operationController = OperationController.getInstance();
        //this.supportedOperationsKeyTag = this.getSupportedOperationsKeyTag();
    }

    public static OperationModel getInstance() {
        return instance == null ? instance = new OperationModel() : instance;
    }

    @Override
    public Set<String> getSupportedOperations() {
        return operations;
    }
}
