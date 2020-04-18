package exceptions;

public class BracketsException extends ParsingException {
    public BracketsException(String message, String previous, int pos, char ch, String after) {
        super(message, previous , pos + 1, ch, after);
    }
}