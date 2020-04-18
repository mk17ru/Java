/*package expression.unary;

import expression.CommonExpression;

public final class Sqrt extends UnaryEvaluate {

    public Sqrt(CommonExpression first) {
        super(first);
    }

    @Override
    protected int compute(int x) {
        int ans = 1;
        while (Integer.MAX_VALUE / ans > ans && ans * ans < x) {
            ans++;
        }
        return ans;
    }

    @Override
    protected String getOperationType() {
        return "sqrt";
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
}*/