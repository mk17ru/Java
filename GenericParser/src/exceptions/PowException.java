package exceptions;

public class PowException extends EvaluatingException {
    public PowException(int val, int deg) {
        super("Uncorrectable argument for pow: " + val + " " + deg);
    }
}