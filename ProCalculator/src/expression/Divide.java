package expression;

public class Divide extends BinaryEvaluate {

    public Divide(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int compute(int first, int second) {
        return first / second;
    }

    @Override
    public double compute(double first, double second) {
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

    public String toMiniString() {
        return super.toMiniString();
    }
}