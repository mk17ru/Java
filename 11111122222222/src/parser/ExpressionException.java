package parser;

import exceptions.BracketsException;

public class ExpressionException extends RuntimeException {
    public ExpressionException(final String message) {
        super(message);
    }
}