package exceptions;

public class OperationException extends ParsingException {
    public OperationException(String message, int pos, String data) {
        super(message, pos, data);
    }
}