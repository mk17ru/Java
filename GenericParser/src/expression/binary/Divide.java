package expression.binary;

import expression.CommonExpression;
import operations.Operation;

public class Divide<T> extends BinaryEvaluate<T> {

    public Divide(CommonExpression<T> first, CommonExpression<T> second, Operation<T> oper) {
        super(first, second, oper);
    }

    @Override
    public T compute(T first, T second) {
        return operation.div(first, second);
    }

    @Override
    protected String getOperationType() {
        return " / ";
    }

}

