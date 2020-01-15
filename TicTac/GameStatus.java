package TicTac;

import java.util.*;
public final class GameStatus {

    public static int N = 3;
    public static int M = 3;
    public static int K = 3;
    public static Player PLAYER1, PLAYER2;
    public GameStatus(final int n, final int m, final int k, final Player player1, final Player player2) {
        N = n;
        M = m;
        K = k;
        if (player1 == null || player2 == null) {

        }
        PLAYER1 = player1;
        PLAYER2 = player2;
    }
}
