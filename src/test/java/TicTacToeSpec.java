import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TicTacToeSpec {

    private TicTacToe ticTacToe;


    @BeforeEach
    void setUp() {
        ticTacToe = new TicTacToe();
    }

    @Test
    void whenXOutSideBoardThenThrowException() {
        assertThrows(RuntimeException.class, () -> ticTacToe.play(5, 2));
    }

    @Test
    void whenYOutsideBoardThenRuntimeException() {
        assertThrows(RuntimeException.class, () -> ticTacToe.play(2, 5));
    }

    @Test
    void whenOccupiedThenThrowsException() {
        assertThrows(RuntimeException.class, () -> {
            ticTacToe.play(1, 1);
            ticTacToe.play(1, 1);
        });
    }

    @Test
    void givenFirstTurnNextPlayerShouldBeX() {
        assertEquals("X", ticTacToe.nextPlayer());
    }

    @Test
    void givenLastTurnWasXWhenNextPlayerThenO() {
        ticTacToe.play(1, 1);
        assertEquals("O", ticTacToe.nextPlayer());
    }

    @Test
    void whenPlayThenNoWinner() {
        assertEquals(ticTacToe.play(1, 1), "NO winner");
    }
}
