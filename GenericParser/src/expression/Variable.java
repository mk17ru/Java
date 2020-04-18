package expression;

public class Variable<T> implements CommonExpression<T> {
    private String var;

    public Variable(String var) {
        this.var = var;
    }

    /*public String getVar() {
        return var;
    }*/

    public T evaluate(T x, T y, T z) {
        switch (var) {
            case "x":
                return x;
            case "y":
                return y;
            default:
                return z;
        }
    }

}