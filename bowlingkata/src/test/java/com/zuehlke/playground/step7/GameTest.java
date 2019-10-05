package com.zuehlke.playground.step7;

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
        assertEquals(0, game.score()); // 0
    }

    @Test
    public void allRolls_0PinEach() {
        rollMultipleTimes(20, 0);
        assertEquals(0, game.score()); // 0 = 20 * (0 + 0)
    }

    @Test
    public void allRolls_1PinEach() {
        rollMultipleTimes(20, 1);
        assertEquals(20, game.score()); // 20 = 10 * (1 + 1)
    }

    @Test
    public void allRolls_allStrikes() {
        rollMultipleTimes(10, 10);
        game.roll(10);
        game.roll(10);
        assertEquals(300, game.score()); // 300 = 10 * (10 + 10 + 10)
    }

    @Test
    public void allRolls_allSpares() {
        rollMultipleTimes(20, 5);
        game.roll(5);
        assertEquals(150, game.score()); // 150 = 10 * (5 + 5 + 5)
    }

    @Test
    public void mixedRolls() {
        game.roll(10); // strike, frame 1
        rollMultipleTimes(10, 1); // 1, frame 2-6
        game.roll(4);
        game.roll(6); // spare, frame 7
        rollMultipleTimes(6, 2); // 0, frame 8-10
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

    private void rollMultipleTimes(int rolls, int numberOfPins) {
        for (int i = 0; i < rolls; i++) {
            game.roll(numberOfPins);
        }
    }

    public void strike() {
        game.roll(10);
    }

    public void spare(int roll1) {
        roll(roll1, 10 - roll1);
    }

    public void roll(int roll1, int roll2) {
        game.roll(roll1);
        game.roll(roll2);
    }

}
