package expression;

public class RightShift extends BinaryEvaluate {


    public RightShift(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int compute(int first, int second) {
        return first >> second;
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
        return " >> ";
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