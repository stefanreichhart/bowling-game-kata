package com.zuehlke.bowling.step8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    private Game game;

    @BeforeEach
    void createGame() {
        game = assertDoesNotThrow(() -> new Game());
    }

    @Test
    void scoreForNewGame() {
        assertEquals(0, game.score()); // 0
    }

    @Test
    void allRolls_0PinEach() {
        repeat(10, () -> frame(0, 0));
        assertEquals(0, game.score()); // 0 = 10 * (0 + 0)
    }

    @Test
    void allRolls_1PinEach() {
        repeat(10, () -> frame(1, 1));
        assertEquals(20, game.score()); // 20 = 10 * (1 + 1)
    }

    @Test
    void allRolls_2PinEach() {
        repeat(10, () -> frame(2, 2));
        assertEquals(40, game.score()); // 40 = 10 * (2 + 2)
    }

    @Test
    void allRolls_allStrikes() {
        repeat(10, () -> strike());
        strike(); // bonus for last frame
        strike(); //
        assertEquals(300, game.score()); // 300 = 10 * (10 + 10 + 10)
    }

    @Test
    void allRolls_allSpares() {
        repeat(10, () -> spare(5));
        game.roll(5); // bonus for the last spare
        assertEquals(150, game.score()); // 150 = 10 * (5 + 5 + 5)
    }

    @Test
    void mixedRolls() {
        strike();
        repeat(5, () -> frame(1, 1));
        frame(4, 6);
        repeat(3, () -> frame(2, 2));
        assertEquals(46, game.score()); // (10 + 2) + (10 * 1) + (4 + 6 + 2) + (6 * 2)
    }

    @Test
    void mixedRolls_realGame() {
        strike(); // frame 1
        frame(4, 4);
        frame(7, 1);
        frame(2, 7);
        spare(3); // frame 5
        strike();
        strike();
        strike();
        frame(1, 0);
        strike(); // frame 10, finish
        frame(9, 1);
        assertEquals(146, game.score()); // (10 + 4 + 4) + (4 + 4) + (7 + 1) + (2 + 7) + (3 + 7 + 10) + (10 + 10 + 10) + (10 + 10 + 1) + (10 + 1 + 0) + (1 + 0) + (10 + 9 + 1)
    }

    private void repeat(int times, Runnable runnable) {
        for (int i = 0; i < times; i++) {
            runnable.run();
        }
    }

    private void strike() {
        game.roll(10);
    }

    private void spare(int roll1) {
        frame(roll1, 10 - roll1);
    }

    private void frame(int roll1, int roll2) {
        game.roll(roll1);
        game.roll(roll2);
    }

}
