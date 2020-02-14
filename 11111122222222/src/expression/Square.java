package expression;

public final class Square extends UnaryEvaluate {

    public Square(CommonExpression first) {
        super(first);
    }

    @Override
    protected int compute(int x) {
        return x * x;
    }

    @Override
    protected String getOperationType() {
        return "square";
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
        return compute((int) x);
    }
}