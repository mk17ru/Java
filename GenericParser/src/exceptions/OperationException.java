package exceptions;

public class OperationException extends ParsingException {
    public OperationException(String message, String previous, int pos, char ch, String after) {
        super(message, previous, pos, ch, after);
    }

}