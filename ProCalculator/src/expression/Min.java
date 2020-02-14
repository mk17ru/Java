package expression;

public class Min extends BinaryEvaluate {


    public Min(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int compute(int first, int second) {
        if (first < second) {
            return first;
        } else {
            return second;
        }
    }

    public String toMiniString() {
        return super.toMiniString();
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    protected String getOperationType() {
        return " min ";
    }

    @Override
    public boolean isOrdered() {
        return false;
    }

    @Override
    public double compute(double first, double second) {
        return compute((int) first, (int) second);
    }
}