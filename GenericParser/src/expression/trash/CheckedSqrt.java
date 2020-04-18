/*package expression.unused;

import exceptions.SqrtException;
import expression.CommonExpression;
import expression.unary.UnaryEvaluate;

public final class CheckedSqrt extends UnaryEvaluate {

    public CheckedSqrt(CommonExpression first) {
        super(first);
    }

    @Override
    protected int compute(int x) {
        if (x < 0) {
            throw new SqrtException();
        }
        int ans = 1;
        while (Integer.MAX_VALUE / ans > ans && ans * ans <= x) {
            ans++;
        }
        return ans - 1;
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