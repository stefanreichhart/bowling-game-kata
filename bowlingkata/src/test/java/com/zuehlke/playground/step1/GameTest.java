package com.zuehlke.playground.step1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void scoreForNewGame() {
        Game game = new Game();
        assertEquals(0, game.score());
    }

    @Test
    public void allRolls_0PinEach() {
        Game game = new Game();
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }
        assertEquals(0, game.score()); // 0 = 20 * (0 + 0)
    }

    @Test
    public void allRolls_1PinEach() {
        Game game = new Game();
        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }
        assertEquals(20, game.score()); // 20 = 10 * (1 + 1)
    }

}
