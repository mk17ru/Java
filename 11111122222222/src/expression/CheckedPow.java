package expression;

import exceptions.OverflowException;
import exceptions.PowException;

public class CheckedPow extends BinaryEvaluate {

    public CheckedPow(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int compute(int first, int second) {
        if (second < 0 || first == 0 && second == 0) {
            throw new PowException(first, second);
        }
        if (first == 0) {
            return 0;
        }
        int a;
        if (first < 0) {
            a = -first;
        } else {
            a = first;
        }
        if (a <= 1 && second > 2) {
            second = (second - 1) % 2 + 1;
        }
        int ans = 1;
        /*while (second > 0) {
            if (second % 2 == 1) {
                int check = ans;
                ans *= first;
                if (ans / first != check){
                    throw new OverflowException();
                }
                second--;
            } else {
                int check = first;
                first = first * first;
                if (first / check != check){
                    throw new OverflowException();
                }
                second>>=1;
            }
        }*/
        for (int i = 1; i <= second; ++i) {
            if (ans < Integer.MIN_VALUE / a || Integer.MAX_VALUE / a < ans) {
                throw new OverflowException();
            }
            ans *= first;
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
        return " ** ";
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