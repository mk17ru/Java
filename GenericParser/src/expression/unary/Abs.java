package expression.unary;

import expression.CommonExpression;
import operations.Operation;

public final class Abs<T> extends UnaryEvaluate<T> {

    public Abs(CommonExpression<T> first, Operation<T> oper) {
        super(first, oper);
    }

    @Override
    protected T compute(T x) {
        return operation.abs(x);
    }

}