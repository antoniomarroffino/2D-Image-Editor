package ch.supsi.imageEditor.backend.exception;

public class OperationNotSupportedException extends Exception {
    public OperationNotSupportedException(String message) {
        super(message);
    }
}
