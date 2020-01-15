package TicTac;

public class PlayerPosition implements Position {
    private final Position position;

    public PlayerPosition(final Position position) {
        this.position = position;
    }

    @Override
    public boolean isValid(final Move move) {
        return position.isValid(move);
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return position.getCell(r, c);
    }

    public String toString() {
        return position.toString();
    }
}
