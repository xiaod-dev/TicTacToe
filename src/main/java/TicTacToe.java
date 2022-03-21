import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class TicTacToe {
    private final HashSet<Step> steps = new HashSet<>();

    private Player nextPlayer = Player.X;

    private static final int MINIMAL_STEP = 3;

    public Result play(int x, int y) {
        checkAxis(x);
        checkAxis(y);

        setBox(x, y);
        togglePlayer();
        return checkWinner();
    }

    private void togglePlayer() {
        if (Player.X.equals(nextPlayer)) {
            nextPlayer = Player.O;
        } else {
            nextPlayer = Player.X;
        }
    }

    private Result checkWinner() {
        return checkHorizontal();
    }

    private Result checkHorizontal() {
        if (steps.size() < MINIMAL_STEP) {
            return Result.NO_WINNER;
        }
//        List<Player> players = List.of(Player.X, Player.O);
        // fixed height from 1 to 3
        List<Integer> listOfY = List.of(1, 2, 3);
        boolean playerXResult = listOfY.stream().anyMatch(integer -> isCompleteHorizontalLine(integer, Player.X));

        boolean playerOResult = listOfY.stream()
                .anyMatch(integer -> isCompleteHorizontalLine(integer, Player.O));
        if (playerXResult) return Result.X_WINNER;
        if (playerOResult) return Result.O_WINNER;
        return Result.NO_WINNER;
    }

    private boolean isCompleteHorizontalLine(Integer height, Player player) {
        return steps.stream()
                .filter(step -> step.player.equals(player))
                .filter(step -> step.piece.y == height)
                .mapToInt(step -> step.piece.x).sum() == 6;
    }

    private void setBox(int x, int y) {
        var piece = new Piece(x, y);
        if (steps.stream().anyMatch(step -> piece.equals(step.piece))) {
            throw new RuntimeException("occupied!");
        } else {
            steps.add(new Step(nextPlayer, piece));
        }
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) throw new RuntimeException("axis should > 0 and <= 3");
    }

    public Player nextPlayer() {
        return nextPlayer;
    }

    enum Player {
        X(), O();

        Player() {
        }
    }

    enum Result {
        X_WINNER("Winner is X"),
        O_WINNER("Winner is O"),
        NO_WINNER("No winner");
        private String desc;

        Result(String desc) {
            this.desc = desc;
        }
    }

    static class Step {
        private final Player player;
        private final Piece piece;

        public Step(Player player, Piece piece) {
            this.player = player;
            this.piece = piece;
        }
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
