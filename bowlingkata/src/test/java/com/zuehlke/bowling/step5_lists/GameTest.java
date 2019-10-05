package com.zuehlke.bowling.step5_lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    private Game game;

    @BeforeEach
    public void createGame() {
        game = assertDoesNotThrow(() -> new Game());
    }

    @Test
    public void scoreForNewGame() {
        assertEquals(0, game.score()); // 0
    }

    @Test
    public void allRolls_0PinEach() {
        rollMultipleTimes(20, 0);
        assertEquals(0, game.score()); // 0 = 20 * (0 + 0)
    }

    @Test
    public void allRolls_1PinEach() {
        rollMultipleTimes(20, 1);
        assertEquals(20, game.score()); // 20 = 10 * (1 + 1)
    }

    @Test
    public void allRolls_2PinEach() {
        rollMultipleTimes(20, 2);
        assertEquals(40, game.score()); // 40 = 10 * (2 + 2)
    }

    @Test
    public void allRolls_allStrikes() {
        rollMultipleTimes(10, 10);
        rollMultipleTimes(2, 10); // bonus for last frame
        assertEquals(300, game.score());  // 300 = 10 * (10 + 10 + 10)
    }

    private void rollMultipleTimes(int rolls, int numberOfPins) {
        for (int i = 0; i < rolls; i++) {
            game.roll(numberOfPins);
        }
    }

}
