package expression;

import exceptions.EvaluatingException;
import exceptions.VariableException;
import parser.*;

import java.util.NoSuchElementException;
import java.util.Objects;

public class Variable implements CommonExpression {
    private String var;

    public Variable(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    public int evaluate(int x) {
        return x;
    }

    public double evaluate(double x) {
        return x;
    }

    public int evaluate(int x, int y, int z) {
        switch (var) {
            case "x":
                return x;
            case "y":
                return y;
            default:
                return z;
        }
    }

    public String toString() {
        return var;
    }

    public String toMiniString() {
        return toString();
    }

    public boolean equals(Object object) {
        if (object != null && object.getClass() == getClass()) {
            Variable v = (Variable) object;
            return var.equals(v.getVar());
        } else {
            return false;
        }
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public boolean isOrdered() {
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(var);
    }
}