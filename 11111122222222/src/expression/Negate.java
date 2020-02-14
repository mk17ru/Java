package expression;


public final class Negate extends UnaryEvaluate {

    public Negate(CommonExpression value) {
        super(value);
    }

    @Override
    protected int compute(int x) {
        return -x;
    }

    @Override
    protected double compute(double x) {
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
}