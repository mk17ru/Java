package parser;

import exceptions.ParsingException;

public class StringSource implements ExpressionSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public String getSource() {
        return data;
    }

    @Override
    public int curPosition() {
        return pos;
    }



}