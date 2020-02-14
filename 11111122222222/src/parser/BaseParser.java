package parser;

import exceptions.BracketsException;
import exceptions.ParsingException;

import java.text.ParseException;

public class BaseParser {
    private ExpressionSource source;
    protected char ch;

    protected BaseParser(final ExpressionSource source) {
        this.source = source;
        nextChar();
    }

    public BaseParser() {

    }

    protected void changeSource(final ExpressionSource source) {
        this.source = source;
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next(true) : '\0';
    }

    protected char nextCharn() {
        return source.hasNext() ? source.next(false) : '\0';
    }

    protected String getSource() {
        return source.getSource();
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected boolean testn(char expected) {
        return nextCharn() == expected;
    }

    protected void expect(final char c) throws ParsingException {

        if (ch != c) {
            if (c == ')' || c == '(') {
                throw new BracketsException("Expected '" + c + "', found '" + ch + "'", curPosition(), getSource());
            } else {
                throw error("Expected '" + c + "', found '" + ch + "'");
            }
        }
        nextChar();
    }

    protected void expect(final String value) throws ExpressionException {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected ParsingException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected int curPosition() {
        return source.curPosition();
    }

    protected void copyDigits(final StringBuilder sb) {
        while (between('0', '9')) {
            sb.append(ch);
            nextChar();
        }
    }

    protected void copyInteger(final StringBuilder sb) throws ParsingException {
        if (test('-')) {
            sb.append('-');
        }
        if (test('0')) {
            sb.append('0');
        } else if (between('1', '9')) {
            copyDigits(sb);
        } else {
            throw error("Invalid number");
        }
    }

    protected void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }


}