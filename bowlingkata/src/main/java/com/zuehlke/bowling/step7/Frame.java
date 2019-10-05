package com.zuehlke.bowling.step7;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private static final int MAX_SCORE = 10;
    private static final int MAX_ROLLS = 2;

    private List<Integer> rolls = new ArrayList<>();

    public void roll(int numberOfPins) {
        rolls.add(numberOfPins);
    }

    public int getScore() {
        return rolls.stream()
                .mapToInt(each -> each)
                .sum();
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
