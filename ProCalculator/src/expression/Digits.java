package expression;

public final class Digits extends UnaryEvaluate {

    public Digits(CommonExpression first) {
        super(first);
    }

    @Override
    protected int compute(int x) {
        int ans = 0;
        while (x != 0) {
            ans += x % 10;
            x /= 10;
        }

        if (ans < 0) {
            ans = -ans;
        }

        return ans;

    }

    @Override
    protected double compute(double x) {
        return compute((int) x);
    }


    @Override
    protected String getOperationType() {
        return "digits";
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