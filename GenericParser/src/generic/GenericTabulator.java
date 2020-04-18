package generic;

import exceptions.EvaluatingException;
import exceptions.ParsingException;
import expression.CommonExpression;
import operations.*;
import parser.ExpressionParser;

import java.util.InputMismatchException;
import java.util.Map;


public class GenericTabulator implements Tabulator {
    private static final Map<String, Operation<?>> MODES = Map.of(
            "bi", new BigIntegerOperation(),
            "i", new IntegerOperation(true),
            "d", new DoubleOperation(),
            "u", new IntegerOperation(false),
            "l", new LongOperation(),
            "s", new ShortOperation()
    );

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Operation<?> operation = MODES.get(mode);
        if (operation == null) {
            throw new InputMismatchException("Invalid operation type " + mode);
        }
        return getTab(expression, x1, x2, y1, y2, z1, z2, operation);
    }

    private <T> Object[][][] getTab(String expression, int x1, int x2, int y1, int y2, int z1, int z2, Operation<T> operation) throws ParsingException {
        Object[][][] values = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        CommonExpression<T> func = new ExpressionParser<T>(operation).parse(expression);
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    try {
                        values[x - x1][y - y1][z - z1] = func.evaluate(
                                operation.parseValue(Integer.toString(x)),
                                operation.parseValue(Integer.toString(y)),
                                operation.parseValue(Integer.toString(z))
                        );
                    } catch (EvaluatingException e) {
                        //values[x - x1][y - y1][z - z1] = null;
                    }
                }
            }
        }
        return values;
    }
}