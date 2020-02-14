package expression;

public class Const implements CommonExpression {
    private Number val;

    public Const(int val) {
        this.val = val;
    }

    public Const(double val) {
        this.val = val;
    }

    public int evaluate(int x) {
        return val.intValue();
    }

    public double evaluate(double x) {
        return val.doubleValue();
    }

    public Number getValue() {
        return val;
    }

    public int evaluate(int x, int y, int z) {
        return val.intValue();
    }

    public String toString() {
        return val.toString();
    }

    public String toMiniString() {
        return toString();
    }


    public boolean equals(Object object) {
        if (object != null && object.getClass() == getClass()) {
            Const c = (Const) object;
            return val.equals(c.getValue());
        } else {
            return false;
        }
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public boolean isOrdered() {
        return false;
    }

    public int hashCode() {
        return val.hashCode();
    }
}