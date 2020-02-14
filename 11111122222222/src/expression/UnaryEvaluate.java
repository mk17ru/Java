package expression;

import java.util.Objects;

public abstract class UnaryEvaluate implements CommonExpression {

    private final CommonExpression value;

    public UnaryEvaluate(CommonExpression value) {
        this.value = value;
    }

    protected abstract int compute(int x);

    protected abstract double compute(double x);

    public int evaluate(int x) {
        return compute(value.evaluate(x));
    }

    public double evaluate(double x) {
        return compute(value.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return compute(value.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + getOperationType() + value + ")";
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getOperationType()).append(value.toMiniString());
        return sb.toString();
    }

    protected abstract String getOperationType();

    @Override
    public int hashCode() {
        return value.hashCode() * 43 + getOperationType().hashCode() * -31;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof UnaryEvaluate) {
            UnaryEvaluate new_second = (UnaryEvaluate) object;
            return Objects.equals(value, new_second.value)
                    && Objects.equals(getOperationType(), new_second.getOperationType());
        } else {
            return false;
        }
    }
}