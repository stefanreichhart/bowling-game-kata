package com.zuehlke.bowling.step5;

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
        return isStrike() || rolls.size() == MAX_ROLLS;
    }

    public boolean isStrike() {
        return rolls.size() == 1 && getScoreOfFirstRoll() == MAX_SCORE;
    }

    int getScoreOfFirstRoll() {
        return rolls.get(0);
    }

}
