package com.zuehlke.bowling.vSTRE;


import java.util.ArrayList;
import java.util.List;

/* stefan's playground for an solution */
public class Frame {

    private static final int MAX_NUMBER_OF_ROLLS = 2;
    private static final int MAX_NUMBER_OF_PINS = 10;

    private List<Integer> rolls = new ArrayList<>();


    public int scoreOfFirstRoll() {
        return rolls.get(0);
    }

    private int scoreOfSecondRoll() {
        return rolls.get(1);
    }

    public int score() {
        return rolls.stream()
                .reduce(0, (next, sum) -> sum + next)
                .intValue();
    }

    public int rolls() {
        return rolls.size();
    }

    public void roll(int numberOfPins) {
        rolls.add(numberOfPins);
    }

    public boolean isComplete() {
        return isStrike() || rolls.size() == MAX_NUMBER_OF_ROLLS;
    }

    public boolean isStrike() {
        return rolls.size() == 1 && scoreOfFirstRoll() == MAX_NUMBER_OF_PINS;
    }

    public boolean isSpare() {
        return !isStrike()
                && isComplete()
                && (scoreOfFirstRoll() + scoreOfSecondRoll() == MAX_NUMBER_OF_PINS);
    }

}
