package TicTac;

import java.util.*;

public class Game {
    private final boolean log;
    private Player player1, player2;

    public Game(final boolean log) {
        this.log = log;
        this.player1 = GameStatus.PLAYER1;
        this.player2 = GameStatus.PLAYER2;
    }

    public Game(final boolean log, final boolean order) {
        this.log = log;
        if (order) {
            this.player1 = GameStatus.PLAYER1;
            this.player2 = GameStatus.PLAYER2;
        } else {
            this.player1 = GameStatus.PLAYER2;
            this.player2 = GameStatus.PLAYER1;
        }
    }
    public int play(Board board) {
        while (true) {
            final int result1 = move(board, player1, 0);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, player2, 1);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        log("Position:\n" + board);
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move, no);
        log("Player " + (no + 1) + " move: " + move);
        //log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + (no + 1) + " won");
            return (no + 1);
        } else if (result == Result.LOSE) {
            log("Player " + (no + 1) + " lose");
            return (no % 2) + 1;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
