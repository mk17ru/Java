package exceptions;

public class LogarithmException extends EvaluatingException {
    public LogarithmException(int val, int deg) {
        super("Uncorrectable argument for logarithm: " + val + " " + deg);
    }
}