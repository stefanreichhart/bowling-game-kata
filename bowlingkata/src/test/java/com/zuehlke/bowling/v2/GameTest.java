package com.zuehlke.bowling.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    @Test
    void test_noPoints() {
        Game game = new Game();
        int numberOfPins = 0;
        for (int i = 0; i < 20; i++) {
            game.roll(numberOfPins);
        }
        assertEquals(0, game.score());
    }

    @Test
    void test_allOnes() {
        Game game = new Game();
        for (int i = 0; i< 20; i++) {
            game.roll(1);
        }
        assertEquals(20, game.score());
    }

}