package com.zuehlke.bowling.step6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    private Game game;

    @BeforeEach
    public void createGame() {
        game = new Game();
    }

    @Test
    public void scoreForNewGame() {
        assertEquals(0, game.score());
    }

    @Test
    public void allRolls_0PinEach() {
        repeat(10, () -> roll(0,0));
        assertEquals(0, game.score()); // 0 = 10 * (0 + 0)
    }

    @Test
    public void allRolls_1PinEach() {
        repeat(10, () -> roll(1,1));
        assertEquals(20, game.score()); // 20 = 10 * (1 + 1)
    }

    @Test
    public void allRolls_allStrikes() {
        repeat(10, () -> strike());
        strike();
        strike();
        assertEquals(300, game.score()); // 300 = 10 * (10 + 10 + 10)
    }

    @Test
    public void allRolls_allSpares() {
        repeat(10, () -> spare(5));
        game.roll(5);
        assertEquals(150, game.score()); // 150 = 10 * (5 + 5 + 5)
    }

    @Test
    public void mixedRolls() {
        strike();
        repeat(5, () -> roll(1,1));
        roll(4,6);
        repeat(3, () -> roll(2,2));
        assertEquals(46, game.score()); // (10 + 2) + (10 * 1) + (4 + 6 + 2) + (6 * 2)
    }

    @Test
    public void mixedRolls_realGame() {
        strike(); // frame 1
        roll(4, 4);
        roll(7, 1);
        roll(2, 7);
        spare(3); // frame 5
        strike();
        strike();
        strike();
        roll(1, 0);
        strike(); // frame 10, finish
        roll(9, 1);
        assertEquals(146, game.score()); // (10 + 4 + 4) + (4 + 4) + (7 + 1) + (2 + 7) + (3 + 7 + 10) + (10 + 10 + 10) + (10 + 10 + 1) + (10 + 1 + 0) + (1 + 0) + (10 + 9 + 1)
    }

    private void repeat(int times, Runnable runnable) {
        for (int i=0; i<times; i++) {
            runnable.run();
        }
    }

    private void strike() {
        game.roll(10);
    }

    private void spare(int roll1) {
        roll(roll1, 10 - roll1);
    }

    private void roll(int roll1, int roll2) {
        game.roll(roll1);
        game.roll(roll2);
    }

}
