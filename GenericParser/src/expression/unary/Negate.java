package expression.unary;


import expression.CommonExpression;
import operations.Operation;

public final class Negate<T> extends UnaryEvaluate<T> {

    public Negate(CommonExpression<T> value, Operation<T> oper) {
        super(value, oper);
    }

    @Override
    protected T compute(T x) {
        return operation.neg(x);
    }

}