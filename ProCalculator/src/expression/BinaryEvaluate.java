package expression;

import exceptions.EvaluatingException;

import java.util.Objects;

public abstract class BinaryEvaluate implements CommonExpression {
    private CommonExpression argLeft;
    private CommonExpression argRight;

    public BinaryEvaluate(CommonExpression first, CommonExpression second) {
        this.argLeft = first;
        this.argRight = second;
    }

    protected abstract int compute(int x, int y) throws EvaluatingException;


    @Override
    public int evaluate(int x, int y, int z) throws EvaluatingException {
        return compute(argLeft.evaluate(x, y, z), argRight.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) throws EvaluatingException {
        return compute(argLeft.evaluate(x), argRight.evaluate(x));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("(");
        stringBuilder.append(argLeft.toString());
        stringBuilder.append(getOperationType());
        stringBuilder.append(argRight.toString());
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public String toMiniString() {
        StringBuilder stringBuilder = new StringBuilder(CreateString(argLeft, false));
        stringBuilder.append(getOperationType());
        stringBuilder.append(CreateString(argRight, true));
        return stringBuilder.toString();
    }

    protected abstract String getOperationType();

    protected abstract double compute(double x, double y) throws EvaluatingException;

    public boolean equals(Object object) {
        if (object instanceof BinaryEvaluate) {
            BinaryEvaluate exp = (BinaryEvaluate) object;
            return Objects.equals(this.getClass(), object.getClass()) &&
                    Objects.equals(argLeft, exp.argLeft) && Objects.equals(argRight, exp.argRight);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return argLeft.hashCode() * 31 + argRight.hashCode() * 43
                + getOperationType().hashCode() * -31;
    }

    private String CreateString(CommonExpression exp, boolean isSecond) {
        if (getPriority() > exp.getPriority()
                || ((isOrdered() || exp.isOrdered()) && getPriority() == exp.getPriority() && isSecond)) {
            return '(' + exp.toMiniString() + ')';
        }
        return exp.toMiniString();
    }

    @Override
    public double evaluate(double x) throws EvaluatingException {
        return compute(argLeft.evaluate(x), argRight.evaluate(x));
    }
}