package ch.supsi.imageEditor.frontend.exception;

public class LanguageNotSupportedException extends RuntimeException {
    public LanguageNotSupportedException(String message) {
        super(message);
    }
}
