package ch.supsi.imageEditor.backend.dataaccess.operation;

import java.util.Properties;
import java.util.Set;

public interface OperationDataAccessInterface {
    Properties getOperationProperties();
    Set<String> getOperationsTag();
}
