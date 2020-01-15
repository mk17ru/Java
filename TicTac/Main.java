package TicTac;
import java.io.PrintStream;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        int result;
        do {
            List<Player> playerList = new ArrayList<Player>();
            Scanner sc = new Scanner(System.in);
            int n, m, k, cnt = 0;
            boolean mode;
            try {
                System.out.println("Enter n, m, k:");
                n = sc.nextInt();
                m = sc.nextInt();
                k = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Uncorrectable input");
                continue;
            }

            if (k <= 0 || m <= 0 || n <= 0  || n > 1000 || m > 1000) {
                System.out.println("Uncorrectable data");
                continue;
            }

            try {
                System.out.println("Enter mod(true, false):");
                mode = sc.nextBoolean();
                if (mode) {
                    System.out.println("Enter numbers of games");
                    cnt = sc.nextInt();
                }
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Uncorrectable input");
                continue;
            }


            if (mode && cnt <= 0) {
                System.out.println("Uncorrectable data");
                continue;
            }

            for (int i = 1; i <= 2; ++i) {
                System.out.println("Enter " + i + " player type:");
                String s = sc.next();
                switch (s) {
                    case "RP":
                        playerList.add(new RandomPlayer());
                        break;
                    case "SP":
                        playerList.add(new SequentialPlayer());
                        break;
                    case "HP":
                        playerList.add(new HumanPlayer());
                        break;
                    default:
                        System.out.println("error input");
                        --i;
                }
                sc.nextLine();
            }
            if (mode) {
                new GameStatus(n, m, k, playerList.get(0), playerList.get(1));
                Game game;
                int count1 = 0, count2 = 0;
                while (count1 < cnt && count2 < cnt) {
                    boolean turn = (count1 + count2) % 2 == 0;
                    if (turn) {
                        game = new Game(true, true);
                        System.out.println();
                        System.out.println(1 + " player is X, " + 2 + " player is O");
                    } else {
                        game = new Game(true, false);
                        System.out.println();
                        System.out.println(1 + " player is O, " + 2 + " player is X");
                    }
                    result = game.play(new TicTacToeBoard(n, m, k, playerList.size()));
                    if (result == -1) {
                        break;
                    }
                    if (result == 0) {
                        System.out.println("Game result: DRAW");
                    } else if (result == 1 && turn || result == 2 && !turn) {
                            System.out.println("Game result: winner is " + 1 + " person");
                            count1++;
                        }
                        else {
                            System.out.println("Game result: winner is " + 2 + " person");
                            count2++;
                        }
                    System.out.println("Current Match result: first player: " + count1 + ", second player: " + count2);
                }
                if (count1 == cnt) {
                    System.out.println("Match result: winner is " + 1 + " person");
                } else if (count2 == cnt) {
                    System.out.println("Match result: winner is " + 2 + " person");
                } else
                    System.out.println("Match is failed");
            } else {
                new GameStatus(n, m, k, playerList.get(0), playerList.get(1));
                Game game = new Game(true);
                result = game.play(new TicTacToeBoard(n, m, k, playerList.size()));
                if (result == 0) {
                    System.out.println("Game result: DRAW");
                } else if (result != -1) {
                    System.out.println("Game result: winner is " + result + " person");
                }
            }
        } while (true) ;
    }
}
