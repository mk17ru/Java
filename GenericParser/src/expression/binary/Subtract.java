package expression.binary;

import expression.CommonExpression;
import expression.binary.BinaryEvaluate;
import operations.Operation;

public class Subtract<T> extends BinaryEvaluate<T> {


    public Subtract(CommonExpression<T> first, CommonExpression<T> second, Operation<T> oper) {
        super(first, second, oper);
    }

    @Override
    public T compute(T first, T second) {
        return operation.sub(first, second);
    }

    @Override
    protected String getOperationType() {
        return " - ";
    }

}