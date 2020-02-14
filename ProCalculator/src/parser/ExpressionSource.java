package parser;

import exceptions.ParsingException;

public interface ExpressionSource {
    boolean hasNext();

    char next(boolean readNext);

    ParsingException error(final String message);

    int curPosition();

    String getSource();
}