package exceptions;

public class BracketsException extends ParsingException {
    public BracketsException(String message, int pos, String data) {
        super(message, pos + 1, data);
    }
}