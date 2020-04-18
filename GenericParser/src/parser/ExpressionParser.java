package parser;

import exceptions.*;
import expression.*;
import expression.binary.*;
import expression.unary.*;
import operations.Operation;

public class ExpressionParser<T> extends BaseParser implements Parser<T> {

    private final static int PRIORITY_MAX = 4;
    private final Operation<T> computer;

    public ExpressionParser(StringSource stringSource, Operation<T> comp) {
        super(stringSource);
        computer = comp;
    }

    public ExpressionParser(Operation<T> computer) {
        this.computer = computer;
    }


    @Override
    public CommonExpression<T> parse(String expression) throws ParsingException {
        changeSource(new StringSource(expression));
        nextChar();
        CommonExpression<T> result = parseExpression(0);
        skipWhitespace();
        if (isEnd()) {
            return result;
        } else {
            if (curChar() == ')') {
                throw new BracketsException("Expected '(' found ' '", getPrefix(), curPosition(), curChar(), getSuffix());
            } else {
                throw new BracketsException("Expected ')' found ' '", getPrefix(), curPosition(), curChar(), getSuffix());
            }
        }
    }

    private CommonExpression<T> parseExpression(int priority) throws ParsingException {
        skipWhitespace();
        if (priority == PRIORITY_MAX) {
            return parseStore();
        }
        CommonExpression<T> first = parseExpression(priority + 1);
        while (true) {
            skipWhitespace();
            OperationType operation = null;

            if (curChar() == ')') {
                return first;
            }

            if (curChar() == '\0') {
                return first;
            }

            switch(priority) {
                case 0:
                    if (test("min")) {
                        operation = OperationType.MIN;
                    } else if (test("max")) {
                        operation = OperationType.MAX;
                    }
                    break;
                case 1:
                    if (test("+")) {
                        operation = OperationType.ADD;
                    } else if (test("-")) {
                        operation = OperationType.SUB;
                    }
                    break;
                case 2:
                    if (test("*")) {
                        operation = OperationType.MUL;
                    } else if (test("/")) {
                        operation = OperationType.DIV;
                    }
                    break;
            }
            if (operation == null) {
                if (priority == 0) {
                    throw new OperationException("Illegal operation: " + curChar(), getPrefix(), curPosition(), curChar(), getSuffix());
                } else {
                    return first;
                }
            }

            CommonExpression<T> second = parseExpression(priority + 1);
            first = createBinaryOperation(first, second, operation);
        }
    }

    private CommonExpression<T> createBinaryOperation(CommonExpression<T> first, CommonExpression<T> second,
                                                      OperationType operation) throws OperationException {
        switch (operation) {
            case ADD:
                return new Add<>(first, second, computer);
            case SUB:
                return new Subtract<>(first, second, computer);
            case MUL:
                return new Multiply<>(first, second, computer);
            case DIV:
                return new Divide<>(first, second, computer);
            case MIN:
                return new Min<>(first, second, computer);
            case MAX:
                return new Max<>(first, second, computer);
            default:
                throw new OperationException("Illegal operator: " + operation, getPrefix(),
                        curPosition(), curChar(), getSuffix());
        }
    }

    private CommonExpression<T> parseStore() throws ParsingException, OperationException {
        skipWhitespace();
        if (test('(')) {
            CommonExpression<T> arg = parseExpression(0);
            skipWhitespace();
            expect(')');
            return arg;
        }
        if (test('-')) {
            skipWhitespace();
            if (isDigit()) {
                return parseConst(true);
            }
            return new Negate<>(parseStore(), computer);
        }
        if (isDigit()) {
            return parseConst(false);
        }
        if (isVariable()) {
            return parseVariable();
        }
        if (test("abs")) {
            return new Abs<>(parseStore(), computer);
        } else if (test("count")) {
            return new Count<>(parseStore(), computer);
        }

        throw new ParsingException("Expected unary operation, const or variable, but found :",
                getPrefix(), curPosition(), curChar(), getSuffix());
    }

    private CommonExpression<T> parseConst(boolean minus) throws ParsingException {
        StringBuilder sb = new StringBuilder();
        if (minus) {
            sb.append('-');
        }
        copyInteger(sb);
        skipWhitespace();
        if (isDigit()) {
            StringBuilder sbFail = new StringBuilder();
            copyInteger(sbFail);
            throw new ConstException("Spaces in Numbers " + sb + "__" + sbFail, curPosition());
        }
        try {
            return new Const<>(computer.parseValue(sb.toString()));
        } catch (NumberFormatException e) {
            throw new ConstException("Illegal const " + sb.toString(), curPosition());
        }
    }

    private CommonExpression<T> parseVariable() {
        final String var = Character.toString(curChar());
        nextChar();
        return new Variable<T>(var);
    }

}
