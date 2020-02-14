package expression;

public interface CommonExpression extends Expression, TripleExpression, DoubleExpression {
    String toString();

    String toMiniString();

    int getPriority();

    boolean isOrdered();
}