package expression.parser;

import exceptions.ParsingException;
import expression.TripleExpression;
import parser.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParsingException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(-71 % 10);
        ExpressionParser expressionParser = new ExpressionParser();
        TripleExpression expression = expressionParser.parse(scanner.nextLine());
        System.out.println(expression);
    }
}