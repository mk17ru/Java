package parser;

import exceptions.ParsingException;
import expression.*;

public interface Parser<T> {
    CommonExpression<T> parse(String expression) throws ParsingException;
}