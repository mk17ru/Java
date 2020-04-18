package expression;

import exceptions.ParsingException;
import expression.binary.*;
import operations.BigIntegerOperation;
import operations.IntegerOperation;
import parser.*;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParsingException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(-71 % 10);
        IntegerOperation s = new IntegerOperation(false);
        ExpressionParser<Integer> expressionParser = new ExpressionParser<Integer>(s);
        TripleExpression<Integer> expression = expressionParser.parse(scanner.nextLine());
        System.out.println(expression);

    }
}