/*package expression.unused;

import exceptions.DivisionByZeroException;
import exceptions.OverflowException;
import expression.CommonExpression;
import expression.binary.BinaryEvaluate;

public final class CheckedMultiply extends BinaryEvaluate {

    public CheckedMultiply(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int compute(int first, int second) throws DivisionByZeroException, OverflowException {
        if (first == 0 || second == 0) {
            return 0;
        }
        if (first == Integer.MIN_VALUE && second != 1 || second == Integer.MIN_VALUE && first != 1) {
            throw new OverflowException();
        }
        if (second == -1 || first == -1) {
            return first * second;
        }
        if (first > 0 && second > 0) {
            if (Integer.MAX_VALUE / first < second) {
                throw new OverflowException();
            }
        }

        if (first < 0 && second < 0) {
            if (Integer.MAX_VALUE / first > second) {
                throw new OverflowException();
            }
        }
        if (first > 0 && second < 0) {
            if (Integer.MIN_VALUE / first > second) {
                throw new OverflowException();
            }
        }
        if (first < 0 && second > 0) {
            if (Integer.MIN_VALUE / first < second) {
                throw new OverflowException();
            }
        }
        return first * second;
    }

    @Override
    protected String getOperationType() {
        return " * ";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isOrdered() {
        return false;
    }

    @Override
    protected double compute(double x, double y) {
        return x * y;
    }
}*/