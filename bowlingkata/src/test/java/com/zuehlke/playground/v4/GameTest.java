package com.zuehlke.playground.v4;

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
        rollMultipleTimes(20, 0);
        assertEquals(0, game.score());
    }

    @Test
    void test_allOnes() {
        rollMultipleTimes(20, 1);
        assertEquals(20, game.score());
    }

    @Test
    void test_oneSpare() {
        game.roll(5);
        game.roll(5);
        game.roll(3);
        rollMultipleTimes(17, 0);
        assertEquals(16, game.score());
    }

    private void rollMultipleTimes(int numberOfRolls, int numberOfPins) {
        for (int i = 0; i < numberOfRolls; i++) {
            game.roll(numberOfPins);
        }
    }

}