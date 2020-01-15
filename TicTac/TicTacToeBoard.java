package TicTac;

import java.util.*;


public class TicTacToeBoard implements Board, Position {

    private final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.I, '|',
            Cell.H, '-',
            Cell.E, '.'
    );

    private int n, m, k, cntPlayers;
    private final Cell[][] cells;
    private Cell turn;
    private int stepsCount;
    private List<Point> change = List.of(
            new Point(1, 0),
            new Point(0, 1),
            new Point(1, 1),
            new Point(-1, 1)
    );

    private final List<Cell> NEXTSYMBOL = List.of(Cell.X, Cell.O, Cell.I, Cell.H, Cell.E);

    protected TicTacToeBoard(final int n, final int m, final int k, final int cntPlayers) {
        this.cells = new Cell[n + 1][m + 1];
        this.n = n;
        this.m = m;
        this.k = k;
        this.cntPlayers = cntPlayers;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return new PlayerPosition(this);//new TicTacToeBoard(m, n, k, cells, cntPlayers, turn);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move, final int number) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        ++stepsCount;

        for (Point set : change) {
            if (k < counter(move, -set.getX(), -set.getY()) + counter(move, set.getX(), set.getY())) {
                return Result.WIN;
            }
        }

        if(stepsCount == m * n) {
            return Result.DRAW;
        }
        turn = NEXTSYMBOL.get((number + 1) % cntPlayers);
        return Result.UNKNOWN;
    }

    private int counter(Move move, int delx, int dely) {
        int x = move.getRow();
        int y = move.getColumn();
        int count = 0;
        while (x >= 0 && y >= 0 && x < n && y < m  && cells[x][y] == turn) {
            ++count;
            x += delx;
            y += dely;
        }
        return count;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < m; ++i) {
            if (i >= 10) {
                sb.append(" ");
            } else {
                sb.append("  ");
            }
            sb.append(((Integer) i).toString()).append(" ");
        }
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < m; c++) {
                if (c >= 10) {
                    sb.append("  ");
                } else {
                    if (r < 10 || c != 0) {
                        sb.append("  ");
                    } else {
                        sb.append(" ");
                    }
                }
                sb.append(SYMBOLS.get(cells[r][c])).append(" ");
            }
        }
        return sb.toString();
    }
}
