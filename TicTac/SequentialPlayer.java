package TicTac;

import java.io.PrintStream;
import java.util.Random;

public class SequentialPlayer implements Player {

    @Override
    public Move move(final Position position, final Cell cell) {

      //  Board board = (Board) position;
        /*for (int c = 0; c < GameStatus.M; c++) {
            board.makeMove(new Move(1, c, Cell.X), 1);
        }*/
      //  out.println("Position1");
      //  out.println(position);
        for (int r = 0; r < GameStatus.N; r++) {
            for (int c = 0; c < GameStatus.M; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
