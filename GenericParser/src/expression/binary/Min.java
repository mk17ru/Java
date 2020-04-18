package expression.binary;

import expression.CommonExpression;
import expression.binary.BinaryEvaluate;
import operations.Operation;

public class Min<T> extends BinaryEvaluate<T> {


    public Min(CommonExpression<T> first, CommonExpression<T> second, Operation<T> oper) {
        super(first, second, oper);
    }

    @Override
    public T compute(T a, T b) {
        return operation.min(a, b);
    }

    @Override
    protected String getOperationType() {
        return " min ";
    }
}