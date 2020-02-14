package exceptions;

import parser.ExpressionException;

public class VariableException extends ParsingException {
    public VariableException(String val, int pos, String data) {
        super("Invalid variable: '" + val + "'", pos + 1, data);
    }
}