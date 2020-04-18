package expression.unary;

import expression.CommonExpression;
import operations.Operation;

import java.util.Objects;

public abstract class UnaryEvaluate<T> implements CommonExpression<T> {

    private final CommonExpression<T> value;
    protected Operation<T> operation;

    public UnaryEvaluate(CommonExpression<T> value, Operation<T> oper) {
        this.value = value;
        this.operation = oper;
    }

    protected abstract T compute(T x);


    public T evaluate(T x, T y, T z) {
        return compute(value.evaluate(x, y, z));
    }

    public String toString() {
        return "(" + operation + value + ")";
    }

}