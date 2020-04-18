package exceptions;

public class DivisionByZeroException extends EvaluatingException {
    public DivisionByZeroException(String message) {
        super("Division by zero: " + message);
    }
}