package TicTac;

import java.io.*;
import java.util.Scanner;


public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    private int NextInt() {
        try {
            int num = Integer.parseInt(in.next());
            String s = in.nextLine();
            for (int i = 0; i < s.length(); ++i) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    System.out.println("You have excess symbols");
                    return -1;
                }
            }
            return num;
        } catch (IllegalArgumentException e) {
            in.nextLine();
            System.out.println("Uncorrectable symbol " + e.getMessage());
            return -1;
        }
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
         //   out.println("Position");
          //  out.println(position);
            out.println(cell + "'s move");
            int x = -1, y = -1;
            while (x == -1 || y == -1) {
                out.println("Enter row and column:");
                x = NextInt();
                y = NextInt();
            }
            final Move move = new Move(x, y, cell);
            if (position.isValid(move)) {
                return move;
            }
            //final int row = move.getRow();
            //final int column = move.getColumn();
            out.println("Move " + move + " is invalid");
        }
    }
}
