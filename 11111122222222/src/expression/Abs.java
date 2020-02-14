package expression;

public final class Abs extends UnaryEvaluate {

    public Abs(CommonExpression first) {
        super(first);
    }

    @Override
    protected int compute(int x) {
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }

    @Override
    protected String getOperationType() {
        return "abs";
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
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }
}