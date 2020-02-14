package exceptions;

public class ParsingException extends RuntimeException {
    public ParsingException(String message, int pos, String data) {
        super("On position " + pos + ": " + message + ".\nInput: " + data.substring(0, pos - 2 >= 0 ? pos - 2 : 0)
                + " error -> '" + (pos - 2 >= 0 && pos - 2 < data.length() ? data.charAt(pos - 2) : ' ')
                + "' <- error " + data.substring(pos - 1 < data.length() ? pos - 1 : data.length()));
    }

    public ParsingException(String message, int pos) {
        super("On position " + pos + ": " + message);
    }
}