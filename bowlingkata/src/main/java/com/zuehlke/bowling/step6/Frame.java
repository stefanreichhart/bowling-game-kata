package com.zuehlke.bowling.step6;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    public static final int MAX_SCORE = 10;
    public static final int MAX_ROLLS = 2;

    private List<Integer> rolls = new ArrayList<>();

    public void roll(int numberOfPins) {
        rolls.add(numberOfPins);
    }

    public int getScore() {
        int score = 0;
        for (int i = 0; i < rolls.size(); i++) {
            score += rolls.get(i);
        }
        return score;
    }

    public boolean isComplete() {
        return isStrike() || isSpare() || rolls.size() == MAX_ROLLS;
    }

    public boolean isStrike() {
        return rolls.size() == 1 && getScoreOfFirstRoll() == MAX_SCORE;
    }

    public boolean isSpare() {
        return rolls.size() == MAX_ROLLS && getScore() == MAX_SCORE;
    }

    public int getScoreOfFirstRoll() {
        return rolls.get(0);
    }

}
