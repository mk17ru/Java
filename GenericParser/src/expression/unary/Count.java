package expression.unary;


import expression.CommonExpression;
import expression.unary.UnaryEvaluate;
import operations.Operation;

public class Count<T> extends UnaryEvaluate<T> {
    public Count(CommonExpression<T> arg, Operation<T> oper) {
        super(arg, oper);
    }

    public T compute(T a) {
        return operation.count(a);
    }
}