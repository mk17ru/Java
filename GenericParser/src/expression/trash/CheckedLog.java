package expression.trash;

public class CheckedLog  {

    /*public CheckedLog(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int compute(int first, int second) {
        if (second <= 1 || first <= 0) {
            throw new LogarithmException(first, second);
        }
        int ans = 0;

        while (first > 1) {
            if (first < second) {
                break;
            }
            ans++;
            first /= second;
        }
        return ans;
    }

    public String toMiniString() {
        return super.toMiniString();
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    protected String getOperationType() {
        return " // ";
    }

    @Override
    public boolean isOrdered() {
        return false;
    }

    @Override
    public double compute(double first, double second) {
        return compute((int) first, (int) second);
    }*/
}