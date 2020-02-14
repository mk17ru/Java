package parser;

import exceptions.*;
import exceptions.VariableException;
import expression.*;


import java.util.Map;

public class ExpressionParser extends BaseParser implements Parser {

    private int balance = 0;


    public static final Map<OperationType, Integer> level = Map.of(
            OperationType.MINORMAX, 0, OperationType.LEFTSHIFT, 0,
            OperationType.RIGHTSHIFT, 0, OperationType.ADD, 1, OperationType.SUB, 1,
            OperationType.MUL, 2, OperationType.DIV, 2,
            OperationType.VARIABLE, 4, OperationType.LOG, 3, OperationType.POW, 3
    );

    public static final Map<Character, OperationType> book = Map.of(
            '<', OperationType.LEFTSHIFT, '>', OperationType.RIGHTSHIFT,
            '+', OperationType.ADD, '-', OperationType.SUB,
            '*', OperationType.MUL, '/', OperationType.DIV
    );

    public ExpressionParser(StringSource stringSource) {
        super(stringSource);
    }

    public ExpressionParser() {

    }

    @Override
    public CommonExpression parse(String expression) throws ParsingException {
        balance = 0;
        changeSource(new StringSource(expression));
        nextChar();
        return parseExpression(0);
    }

    private CommonExpression parseExpression(int priority) throws ParsingException {
        skipWhitespace();
        if (priority == level.get(OperationType.VARIABLE)) {
            return parseStore();
        }
        CommonExpression first = parseExpression(priority + 1);
        while (true) {
            skipWhitespace();
            OperationType operation = book.get(ch);
            if (ch == '\0') {
                return first;
            }
            if (ch == ')') {
                if (balance == 0) {
                    throw new BracketsException("Expected '(' found ' '", curPosition(), getSource());
                }
                return first;
            }

            if (operation == null) {
                throw new OperationException("Uncorrectable operation: " + ch, curPosition() + 1, getSource());
            }

            if (priority == level.get(OperationType.DIV) + 1 && operation == OperationType.DIV && testn('/')) {
                nextChar();
                operation = OperationType.LOG;
            } else if (priority == level.get(OperationType.MUL) + 1 && operation == OperationType.MUL && testn('*')) {
                nextChar();
                operation = OperationType.POW;
            } else if (priority != level.get(operation)) {
                return first;
            }

            nextChar();

            if (operation == OperationType.LEFTSHIFT) {
                expect('<');
            }
            if (operation == OperationType.RIGHTSHIFT) {
                expect('>');
            }
            CommonExpression second = parseExpression(priority + 1);
            switch (operation) {
                case ADD:
                    first = new CheckedAdd(first, second);
                    break;
                case SUB:
                    first = new CheckedSubtract(first, second);
                    break;
                case MUL:
                    first = new CheckedMultiply(first, second);
                    break;
                case DIV:
                    first = new CheckedDivide(first, second);
                    break;
                case RIGHTSHIFT:
                    first = new RightShift(first, second);
                    break;
                case LEFTSHIFT:
                    first = new LeftShift(first, second);
                    break;
                case POW:
                    first = new CheckedPow(first, second);
                    break;
                case LOG:
                    first = new CheckedLog(first, second);
                    break;
                default:
                    throw new OperationException("Uncorrected operator: " + operation, curPosition(), getSource());
            }
        }
    }

    private CommonExpression parseStore() throws ParsingException {
        if (test('(')) {
            balance++;
            CommonExpression arg = parseExpression(0);
            skipWhitespace();
            expect(')');
            balance--;
            return arg;
        } else if (test('-')) {
            skipWhitespace();
            if (between('0', '9')) {
                return parseConst(true);
            }
            return new Negate(parseStore());
        } else if (test('r')) {
            return parseReverse();
        } else if (test('d')) {
            return parseDigits();
        } else if (test('a')) {
            return parseAbs();
        } else if (test('s')) {
            expect("q");
            if (test('u')) {
                return parseSquare();
            } else {
                return parseSqrt();
            }

        } else if (between('0', '9')) {
            return parseConst(false);
        } else {
            return parseVariable();
        }
    }

    private CommonExpression parseConst(boolean minus) throws ConstException, ExpressionException {
        StringBuilder sb = new StringBuilder();
        if (minus) {
            sb.append('-');
        }
        copyInteger(sb);
        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new ConstException("Illegal const " + sb.toString(), curPosition());
        }
    }


    private CommonExpression parseVariable() throws VariableException {
        skipWhitespace();
        final String var = Character.toString(ch);
        if (isVariable()) {
            return new Variable(var);
        }
        final OperationType operation = book.get(ch);
        if (operation != null) {
            throw new OperationException("No argument for operation: " + operation, curPosition(), getSource());
        }
        throw new VariableException(var, curPosition(), getSource());
    }

    private CommonExpression parseReverse() throws ParsingException {
        expect("everse");
        skipWhitespace();
        return new Reverse(parseStore());
    }

    private CommonExpression parseAbs() throws ParsingException {
        expect("bs");
        if (isVariable()) {
            throw new VariableException(Character.toString(ch), curPosition(), getSource());
        }
        skipWhitespace();
        return new CheckedAbs(parseStore());
    }

    private CommonExpression parseSquare() throws ParsingException {
        expect("are");
        skipWhitespace();
        return new Square(parseStore());
    }

    private CommonExpression parseSqrt() throws ParsingException {
        expect("rt");
        if (isVariable()) {
            throw new VariableException(Character.toString(ch), curPosition(), getSource());
        }
        skipWhitespace();
        return new CheckedSqrt(parseStore());
    }

    /*private CommonExpression parseLog2() throws ParsingException {
        expect("og2");
        if (isVariable()) {
            throw new VariableException("Invalid variable: " + ch);
        }
        skipWhitespace();
        return new CheckedLog(parseStore());
    }

    private CommonExpression parsePow2() throws ParsingException {
        expect("ow2");
        skipWhitespace();
        return new Pow2(parseStore());
    }*/

    private CommonExpression parseDigits() throws ParsingException {
        expect("igits");
        skipWhitespace();
        return new Digits(parseStore());
    }

    private boolean isVariable() {
        return test('x') || test('y') || test('z');
    }
}
