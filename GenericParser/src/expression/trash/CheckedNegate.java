/*package expression.unused;


import exceptions.OverflowException;
import expression.CommonExpression;
import expression.unary.UnaryEvaluate;

public final class CheckedNegate extends UnaryEvaluate {

    public CheckedNegate(CommonExpression value) {
        super(value);
    }

    @Override
    protected int compute(int x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -x;
    }

    @Override
    protected String getOperationType() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 5;
    }

    @Override
    public boolean isOrdered() {
        return false;
    }

    @Override
    protected double compute(double x) {
        return -x;
    }
}*/