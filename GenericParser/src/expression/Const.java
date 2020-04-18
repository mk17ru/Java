package expression;

public class Const<T> implements CommonExpression<T> {
    private final T val;

    public Const(T val) {
        this.val = val;
    }

    public T evaluate(T x, T y, T z) {
        return val;
    }

    public String toString() {
        return val.toString();
    }
}