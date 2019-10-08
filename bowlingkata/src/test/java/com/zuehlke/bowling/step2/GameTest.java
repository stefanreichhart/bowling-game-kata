package com.zuehlke.bowling.step2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    @Test
    void createNewGame() {
        assertDoesNotThrow(() -> new Game());
    }

    @Test
    void scoreForNewGame() {
        Game game = new Game();
        assertEquals(0, game.score());
    }

    @Test
    void allRolls_0PinEach() {
        Game game = new Game();
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }
        assertEquals(0, game.score()); // 0 = 20 * (0 + 0)
    }

    @Test
    void allRolls_1PinEach() {
        Game game = new Game();
        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }
        assertEquals(20, game.score()); // 20 = 10 * (1 + 1)
    }

    @Test
    void allRolls_2PinEach() {
        Game game = new Game();
        for (int i = 0; i < 20; i++) {
            game.roll(2);
        }
        assertEquals(40, game.score()); // 40 = 10 * (2 + 2)
    }

}
