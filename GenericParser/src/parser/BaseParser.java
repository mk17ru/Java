package parser;

import exceptions.BracketsException;
import exceptions.ParsingException;


public class BaseParser {

    private ExpressionSource source;
    private final int BUFFER_SIZE = 17;
    protected char ch;
    private boolean isEnd;
    private final Queue buffer;
    private final Queue lastBuffer;

    protected BaseParser(final ExpressionSource source) {
        this.source = source;
        nextChar();
        buffer = new Queue();
        lastBuffer = new Queue();
    }


    public BaseParser() {
        buffer = new Queue();
        lastBuffer = new Queue();
    }

    protected String getPrefix() {
        StringBuilder sb = new StringBuilder();
        while(lastBuffer.size() > 0) {
            sb.append(lastBuffer.remove());
        }
        return sb.toString();
    }

    protected String getSuffix() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        ch = source.hasNext() ? source.next() : '\0';
        while(i < BUFFER_SIZE) {
            if (ch == '\0') {
                break;
            }
            sb.append(ch);
            ch = source.hasNext() ? source.next() : '\0';
            ++i;
        }
        return sb.toString();
    }

    protected boolean isEnd() {
        return isEnd;
    }

    protected void changeSource(final ExpressionSource source) {
        this.source = source;
    }

    protected void nextChar() {
        lastBuffer.add(ch);
        if (lastBuffer.size() > BUFFER_SIZE) {
            lastBuffer.remove();
        }
        if (buffer.size() > 0) {
            ch = buffer.remove();
        } else {
            ch = source.hasNext() ? source.next() : '\0';
        }

        isEnd = ch == '\0';
    }


    protected boolean test(char expected) {
        if (curChar() == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected boolean test(String expected) {
        if (ch != expected.charAt(0)) {
            return false;
        }
        for (int i = 1; i < expected.length(); ++i) {
            if (i - 1 == buffer.size()) {
                buffer.add(source.hasNext() ? source.next() : '\0');
            }
            if (buffer.get(i - 1) != expected.charAt(i)) {
                return false;
            }
        }
        for (int i = 0; i < expected.length(); ++i) {
            nextChar();
        }
        return true;
    }



    protected void expect(final char c) throws ParsingException {

        if (curChar() != c) {
            if (c == ')' || c == '(') {
                throw new BracketsException("Expected '" + c + "', found '" + curChar() + "'", getPrefix(),
                        curPosition(), curChar(), getSuffix());
            } else {
                throw new ParsingException("Expected '" + c + "', found '" + curChar() + "'", getPrefix(),
                        curPosition(), curChar(), getSuffix());
            }
        }
        nextChar();
    }

    protected void expect(final String value) throws ParsingException {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected char curChar() {
        return ch;
    }


    protected boolean between(final char from, final char to) {
        return from <= curChar() && curChar() <= to;
    }

    protected int curPosition() {
        return source.curPosition();
    }

    protected void copyDigits(final StringBuilder sb) {
        while (between('0', '9')) {
            sb.append(curChar());
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
            throw new ParsingException("Invalid number", getPrefix(), curPosition(), curChar(), getSuffix());
        }
    }

    protected void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }

    protected boolean isDigit() {
        return between('0', '9');
    }

    protected boolean isVariable() {
        return between('x', 'z');
    }

    private static class Queue {
        private char[] queue = new char[10];
        private int head, tail = 0;

        private char get(int pos) {
            return queue[(head + pos) % queue.length];
        }

        private void add(char c) {
            if (queue.length == size() + 1) {
                ensure();
            }
            queue[tail] = c;
            tail = (tail + 1) % queue.length;;
        }

        private int size() {
            return (queue.length + tail - head) % queue.length;
        }

        private char remove() {
            assert size() > 0;

            char c = queue[head];
            head = (head + 1) % queue.length;
            return c;
        }
        private void ensure() {
            int size = size();
            char[] newQueue = new char[queue.length * 2];
            if (tail < head) {
                System.arraycopy(queue, head, newQueue, 0, queue.length - head);
                System.arraycopy(queue, 0, newQueue, queue.length - head, tail);
            } else {
                System.arraycopy(queue, head, newQueue, 0, tail - head);
            }
            head = 0;
            tail = size;
            queue = newQueue;
        }
    }
}