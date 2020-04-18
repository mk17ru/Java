package expression.binary;

import expression.CommonExpression;
import operations.Operation;

public class Add<T> extends BinaryEvaluate<T> {

    public Add(CommonExpression<T> first, CommonExpression<T> second, Operation<T> oper) {
        super(first, second, oper);
    }

    @Override
    public T compute(T first, T second) {
        return operation.add(first, second);
    }

    @Override
    protected String getOperationType() {
        return " + ";
    }

}
