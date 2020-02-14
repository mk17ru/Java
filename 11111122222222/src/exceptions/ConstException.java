package exceptions;

public class ConstException extends ParsingException {
    public ConstException(String message, int pos) {
        super(message, pos);
    }
}