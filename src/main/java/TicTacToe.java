import java.util.HashSet;
import java.util.Objects;

public class TicTacToe {
    private final HashSet<Piece> pieces = new HashSet<>();

    private boolean nextPlayerIsX = true;

    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);

        setBox(x, y);
        nextPlayerIsX = !nextPlayerIsX;
        return "NO winner";
    }

    private void setBox(int x, int y) {
        var piece = new Piece(x, y);
        if (pieces.contains(piece)) {
            throw new RuntimeException("occupied!");
        } else {
            pieces.add(piece);
        }
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3)
            throw new RuntimeException("axis should > 0 and <= 3");
    }

    public String nextPlayer() {
        return nextPlayerIsX ? "X" : "O";
    }

    static class Piece {
        private final int x;

        private final int y;

        public Piece(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Piece piece = (Piece) o;
            return x == piece.x && y == piece.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
