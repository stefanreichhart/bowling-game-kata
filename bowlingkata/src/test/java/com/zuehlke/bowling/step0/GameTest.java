package com.zuehlke.bowling.step0;

import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void scoreForNewGame() {
        Game game = new Game();
        game.score();
    }

}
