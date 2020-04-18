package operations;

import exceptions.DivisionByZeroException;
import exceptions.OverflowException;

public class IntegerOperation implements Operation<Integer> {

    private boolean isCheck;

    public IntegerOperation(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public Integer add(Integer x, Integer y) {
        if (isCheck) {
            checkAdd(x, y);
        }
        return x + y;
    }


    public Integer sub(Integer x, Integer y) {
        if (isCheck) {
            checkSub(x, y);
        }
        return x - y;
    }

    private void checkSub(int a, int b) throws OverflowException {
        if (a == 0 && b == Integer.MIN_VALUE) {
            throw new OverflowException();
        }

        if (a > 0 && b < 0 && Integer.MAX_VALUE + b < a) {
            throw new OverflowException();
        }
        if (a < 0 && b > 0 && Integer.MIN_VALUE + b > a) {
            throw new OverflowException();
        }
    }

    public Integer mul(Integer x, Integer y) {
        if (isCheck) {
            checkMul(x, y);
        }
        return x * y;
    }

    private void checkMul(int a, int b) throws DivisionByZeroException, OverflowException {
        if (((b == Integer.MIN_VALUE && a == -1) || (b != 0) && (a * b / b != a))
        || (a == Integer.MIN_VALUE && b == -1)) {
            throw new OverflowException();
        }
        /*long longRes = (long) first * (long) second;
        if (longRes != (long)(first * second)) {
            throw new OverflowException();
        }*/
    }

    public Integer neg(Integer x) {
        if (isCheck) {
            checkNeg(x);
        }
        return -x;
    }

    private void checkNeg(int x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    public Integer min(Integer a, Integer b) {
        return Integer.min(a, b);
    }

    public Integer max(Integer a, Integer b) {
        return Integer.max(a, b);
    }

    public Integer abs(Integer a) {
        if (isCheck) {
            checkAbs(a);
        }
        return Math.abs(a);
    }

    private void checkAbs(Integer a) {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    public Integer parseValue(String str) { return Integer.parseInt(str); }

    public Integer count(Integer a) {
        return Integer.bitCount(a);
    }

    public Integer div(Integer x, Integer y) {
        if (isCheck) {
            checkDiv(x, y);
        } else if (y == 0) {
            throw new DivisionByZeroException(x + " / " + y);
        }
        return x / y;
    }

    private void checkAdd(Integer first, Integer second) throws OverflowException {

        if (second > 0 && first > 0 && Integer.MAX_VALUE - first < second) {
            throw new OverflowException();
        }
        if (first < 0 && second < 0 && Integer.MIN_VALUE - first > second) {
            throw new OverflowException();
        }
    }

    private void checkDiv(int first, int second) throws DivisionByZeroException, OverflowException {
        if (second == 0) {
            throw new DivisionByZeroException(first + " / " + second);
        }
        if (first == Integer.MIN_VALUE && second == -1) {
            throw new OverflowException();
        }
    }
}
