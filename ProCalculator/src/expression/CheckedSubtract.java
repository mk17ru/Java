package expression;

import exceptions.EvaluatingException;
import exceptions.OverflowException;

public final class CheckedSubtract extends BinaryEvaluate {

    public CheckedSubtract(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int compute(int a, int b) throws OverflowException {
        if (a == 0 && b == Integer.MIN_VALUE) {
            throw new OverflowException();
        }

        if (a > 0 && b < 0 && Integer.MAX_VALUE + b < a) {
            throw new OverflowException();
        }
        if (a < 0 && b > 0 && Integer.MIN_VALUE + b > a) {
            throw new OverflowException();
        }

        return a - b;
    }

    @Override
    protected String getOperationType() {
        return " - ";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean isOrdered() {
        return true;
    }

    @Override
    protected double compute(double x, double y) throws EvaluatingException {
        return x - y;
    }
}