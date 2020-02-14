package expression;

public final class Reverse extends UnaryEvaluate {

    public Reverse(CommonExpression first) {
        super(first);
    }

    @Override
    protected int compute(int x) {
        int ans = 0;
        while (x != 0) {
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        return ans;
    }

    @Override
    protected String getOperationType() {
        return "reverse";
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