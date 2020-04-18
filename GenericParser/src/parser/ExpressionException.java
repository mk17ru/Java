package parser;

import exceptions.BracketsException;
import exceptions.ParsingException;

public class ExpressionException extends Exception {
    public ExpressionException(final String message) {
        super(message);
    }
}