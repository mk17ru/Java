package expression.binary;

import exceptions.EvaluatingException;
import expression.CommonExpression;
import operations.Operation;

import java.util.Objects;

public abstract class BinaryEvaluate<T> implements CommonExpression<T> {
    private CommonExpression<T> argLeft;
    private CommonExpression<T> argRight;
    protected Operation<T> operation;

    public BinaryEvaluate(CommonExpression<T> first, CommonExpression<T> second, Operation<T> oper) {
        this.argLeft = first;
        this.argRight = second;
        this.operation = oper;
    }

    protected abstract T compute(T x, T y) throws EvaluatingException;

    public T evaluate(T x, T y, T z) throws EvaluatingException {
        return compute(argLeft.evaluate(x, y, z), argRight.evaluate(x, y, z));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("(");
        stringBuilder.append(argLeft.toString());
        stringBuilder.append(getOperationType());
        stringBuilder.append(argRight.toString());
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    protected abstract String getOperationType();

}