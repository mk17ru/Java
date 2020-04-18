package parser;

import exceptions.ParsingException;

public interface ExpressionSource {
    boolean hasNext();

    char next();

    int curPosition();

    String getSource();
}