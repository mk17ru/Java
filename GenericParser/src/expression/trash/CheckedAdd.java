package expression.trash;

import exceptions.OverflowException;
import expression.CommonExpression;
import operations.Operation;

public class CheckedAdd {

    /*public CheckedAdd(CommonExpression first, CommonExpression second, Operation oper) {
        super(first, second, oper);
    }

    public int compute(int first, int second) throws OverflowException {
        if (second > 0 && first > 0 && Integer.MAX_VALUE - first < second) {
            throw new OverflowException();
        }
        if (first < 0 && second < 0 && Integer.MIN_VALUE - first > second) {
            throw new OverflowException();
        }
        return first + second;
    }

    public double compute(double a, double b) {
        return a + b;
    }*/
}