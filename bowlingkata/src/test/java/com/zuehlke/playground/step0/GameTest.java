package com.zuehlke.playground.step0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void scoreForNewGame() {
        Game game = new Game();
        assertEquals(0, game.score());
    }

}
