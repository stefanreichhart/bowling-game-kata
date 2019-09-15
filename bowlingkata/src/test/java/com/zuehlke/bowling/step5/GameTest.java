package com.zuehlke.bowling.step5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    private Game game;

    private void rollMultipleTimes(int rolls, int numberOfPins) {
        for (int i = 0; i < rolls; i++) {
            game.roll(numberOfPins);
        }
    }

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
        assertEquals(0, game.score()); // 0 = 20 * 0
    }

    @Test
    public void allRolls_1PinEach() {
        rollMultipleTimes(20, 1);
        assertEquals(20, game.score()); // 1 = 20 * 1
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
        game.roll(10); // strike, frame 1
        game.roll(4); // frame 2
        game.roll(4);
        game.roll(7); // frame 3
        game.roll(1);
        game.roll(2); // frame 4
        game.roll(7);
        game.roll(3); // frame 5, spare
        game.roll(7);
        game.roll(10); // frame 6, strike
        game.roll(10); // frame 7, strike
        game.roll(10); // frame 8, strike
        game.roll(1); // frame 9
        game.roll(0);
        game.roll(10); // frame 10, strike, finish
        game.roll(9);
        game.roll(1);
        assertEquals(146, game.score()); // (10 + 4 + 4) + (4 + 4) + (7 + 1) + (2 + 7) + (3 + 7 + 10) + (10 + 10 + 10) + (10 + 10 + 1) + (10 + 1 + 0) + (1 + 0) + (10 + 9 + 1)
    }

}
