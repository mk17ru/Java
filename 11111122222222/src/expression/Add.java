package expression;

public class Add extends BinaryEvaluate {

    public Add(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int compute(int first, int second) {
        return first + second;
    }

    @Override
    public double compute(double first, double second) {
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

}
