package exceptions;

public class ParsingException extends Exception {
    public ParsingException(String message, String previous, int pos, char ch, String after) {
        super("On position " + pos + ": " + message + " \nInput: " + previous
                + " error -> '" + ch + "' <- error " + after);
    }

    public ParsingException(String message, int pos) {
        super("On position " + pos + ": " + message);
    }
}