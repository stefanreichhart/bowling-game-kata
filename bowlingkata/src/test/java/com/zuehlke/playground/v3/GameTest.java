package com.zuehlke.playground.v3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        this.game = new Game();
    }

    @Test
    void test_noPoints() {
        rollTwentyTimes(0);
        assertEquals(0, game.score());
    }

    @Test
    void test_allOnes() {
        rollTwentyTimes(1);
        assertEquals(20, game.score());
    }

    private void rollTwentyTimes(int numberOfPins) {
        for (int i = 0; i < 20; i++) {
            game.roll(numberOfPins);
        }
    }

}