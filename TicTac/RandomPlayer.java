package TicTac;

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class RandomPlayer implements Player {
    private final PrintStream out;
    private final Random random;

    public RandomPlayer(final Random random, final PrintStream out) {
        this.out = out;
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random(), System.out);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(GameStatus.N);
            int c = random.nextInt(GameStatus.M);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
