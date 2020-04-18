package expression.binary;

import expression.CommonExpression;
import expression.binary.BinaryEvaluate;
import operations.Operation;

public class Max<T> extends BinaryEvaluate<T> {


    public Max(CommonExpression<T> first, CommonExpression<T> second, Operation<T> oper) {
        super(first, second, oper);
    }

    @Override
    public T compute(T a, T b) {
       return operation.max(a, b);
    }

    @Override
    protected String getOperationType() {
        return " max ";
    }
}