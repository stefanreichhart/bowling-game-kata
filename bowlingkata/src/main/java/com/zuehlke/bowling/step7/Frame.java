package com.zuehlke.bowling.step7;

import java.util.ArrayList;
import java.util.List;

class Frame {

    private static final int MAX_SCORE = 10;
    private static final int MAX_ROLLS = 2;

    private final List<Integer> rolls = new ArrayList<>();

    void roll(int numberOfPins) {
        rolls.add(numberOfPins);
    }

    int getScore() {
        return rolls.stream()
                .mapToInt(each -> each)
                .sum();
    }

    boolean isComplete() {
        return isStrike() || isSpare() || rolls.size() == MAX_ROLLS;
    }

    boolean isStrike() {
        return rolls.size() == 1 && getScoreOfFirstRoll() == MAX_SCORE;
    }

    boolean isSpare() {
        return rolls.size() == MAX_ROLLS && getScore() == MAX_SCORE;
    }

    int getScoreOfFirstRoll() {
        return rolls.get(0);
    }

}
