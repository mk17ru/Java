package expression;

import exceptions.DivisionByZeroException;
import exceptions.EvaluatingException;
import exceptions.OverflowException;

public final class CheckedDivide extends BinaryEvaluate {

    public CheckedDivide(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int compute(int first, int second) throws DivisionByZeroException, OverflowException {
        if (second == 0) {
            throw new DivisionByZeroException();
        }
        if (first == Integer.MIN_VALUE && second == -1) {
            throw new OverflowException();
        }
        return first / second;
    }

    @Override
    protected String getOperationType() {
        return " / ";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isOrdered() {
        return true;
    }

    @Override
    protected double compute(double x, double y) throws EvaluatingException {
        return x / y;
    }
}