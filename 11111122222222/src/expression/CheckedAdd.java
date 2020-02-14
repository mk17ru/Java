package expression;

import exceptions.OverflowException;

public class CheckedAdd extends BinaryEvaluate {

    public CheckedAdd(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    public int compute(int first, int second) throws OverflowException {
        if (second > 0 && first > 0 && Integer.MAX_VALUE - first < second) {
            throw new OverflowException();
        }
        if (first < 0 && second < 0 && Integer.MIN_VALUE - first > second) {
            throw new OverflowException();
        }
        return first + second;
    }

    public String toMiniString() {
        return super.toMiniString();
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    protected String getOperationType() {
        return " + ";
    }

    @Override
    public boolean isOrdered() {
        return false;
    }

    public double compute(double a, double b) {
        return a + b;
    }
}