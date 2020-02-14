package parser;

import exceptions.ParsingException;
import expression.*;

public interface Parser {
    CommonExpression parse(String expression) throws ParsingException;
}