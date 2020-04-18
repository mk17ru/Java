package expression.binary;

import expression.CommonExpression;
import expression.binary.BinaryEvaluate;
import operations.Operation;

public class Multiply<T> extends BinaryEvaluate<T> {


    public Multiply(CommonExpression<T> first, CommonExpression<T> second, Operation<T> oper) {
        super(first, second, oper);
    }

    @Override
    public T compute(T first, T second) {
        return operation.mul(first, second);
    }

    @Override
    protected String getOperationType() {
        return " * ";
    }
}