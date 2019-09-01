package com.zuehlke.bowling.vSTRE;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/* stefan's playground for an solution */
public class GameTest {

    private Game game;

    @BeforeEach
    public void createGame() {
        game = new Game();
    }

    @Test
    public void noPointPerRoll() {
        allRollsWithPoints(0);
        assertEquals(0, game.score());
    }

    @Test
    public void onePointPerRoll() {
        allRollsWithPoints(1);
        assertEquals(20, game.score());
    }

    @Test
    public void twoPointPerRoll() {
        allRollsWithPoints(2);
        assertEquals(40, game.score());
    }

    @Test
    public void roll_5_5_3() {
        game.roll(5);
        game.roll(5);
        game.roll(3);
        multipleRollsWithPoints(17, 0);
        assertEquals(16, game.score());
    }

    @Test
    public void roll_10_5_1() {
        game.roll(10);
        game.roll(5);
        game.roll(1);
        multipleRollsWithPoints(18, 0);

        assertEquals(22, game.score());
    }

    @Test
    public void roll_al0_10() {
        multipleRollsWithPoints(19, 0);
        game.roll(10);
        game.roll(1);

        assertEquals(11, game.score());
    }

    private void allRollsWithPoints(int points) {
        multipleRollsWithPoints(20, points);
    }

    private void multipleRollsWithPoints(int rolls, int points) {
        for (int i=0; i<rolls; i++) {
            game.roll(points);
        }
    }

    @Test
    public void assertGameRules0() {
        assertDoesNotThrow(() -> multipleRollsWithPoints(20, 0));
        assertThrows(IllegalStateException.class, () -> multipleRollsWithPoints(21, 0));
        assertThrows(IllegalStateException.class, () -> multipleRollsWithPoints(50, 0));
    }

    @Test
    public void assertGameRulesStrike() {
        assertDoesNotThrow(() -> {
            multipleRollsWithPoints(18, 0);
            game.roll(10);
            game.roll(0);
            game.roll(0);
        });
        assertThrows(IllegalStateException.class, () -> {
            game.roll(0);
        });
    }

    @Test
    public void assertGameRulesSpare() {
        assertDoesNotThrow(() -> {
            multipleRollsWithPoints(19, 0);
            game.roll(10);
            game.roll(1);
        });
        assertThrows(IllegalStateException.class, () -> {
            game.roll(1);
        });
    }

}
