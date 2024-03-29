package com.zuehlke.bowling.step1;

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

}
