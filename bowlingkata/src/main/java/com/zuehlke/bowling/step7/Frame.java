package com.zuehlke.bowling.step7;

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

    public int getScoreForSpareBonus() {
        return rolls.get(0);
    }

    public boolean isComplete() {
        return isStrike() || isSpare() || rolls.size() == MAX_ROLLS;
    }

    public boolean isStrike() {
        return rolls.size() == 1 && rolls.get(0) == MAX_SCORE;
    }

    public boolean isSpare() {
        return rolls.size() == MAX_ROLLS && getScore() == MAX_SCORE;
    }

}
