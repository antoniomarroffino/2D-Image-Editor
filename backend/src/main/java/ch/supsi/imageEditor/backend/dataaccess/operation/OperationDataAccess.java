package ch.supsi.imageEditor.backend.dataaccess.operation;

import ch.supsi.imageEditor.backend.dataaccess.language.LanguageDataAccess;

public class OperationDataAccess implements OperationDataAccessInterface {
    private static OperationDataAccess instance;
    public static OperationDataAccess getInstance() {
        return instance == null ? instance = new OperationDataAccess() : instance;
    }
}
