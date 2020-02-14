package exceptions;

public class SqrtException extends EvaluatingException {
    public SqrtException() {
        super("Uncorrectable argument for sqrt.");
    }
}