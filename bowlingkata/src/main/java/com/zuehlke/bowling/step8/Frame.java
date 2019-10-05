package com.zuehlke.bowling.step8;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private static final int MAX_SCORE = 10;
    private static final int MAX_ROLLS = 2;

    private Frame nextFrame;
    private List<Integer> rolls = new ArrayList<>();

    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    public void roll(int numberOfPins) {
        rolls.add(numberOfPins);
    }

    public int getScore() {
        int score = getScoreOfRolls();
        if (isStrike())  {
            score += getScoreOfStrikeBonus();
        }
        if (isSpare()) {
            score += getScoreOfSpareBonus();
        }
        return score;
    }

    public boolean isComplete() {
        return isStrike() || isSpare() || rolls.size() == MAX_ROLLS;
    }

    private boolean isStrike() {
        return rolls.size() == 1 && getScoreOfFirstRoll() == MAX_SCORE;
    }

    private boolean isSpare() {
        return rolls.size() == MAX_ROLLS && getScoreOfRolls() == MAX_SCORE;
    }

    private int getScoreOfRolls() {
        return rolls.stream()
                .mapToInt(each -> each)
                .sum();
    }

    private int getScoreOfFirstRoll() {
        return rolls.get(0);
    }

    private int getScoreOfStrikeBonus() {
        int score = nextFrame.getScoreOfRolls();
        if (nextFrame.isStrike()) {
            score += nextFrame.nextFrame.getScoreOfFirstRoll();
        }
        return score;
    }

    private int getScoreOfSpareBonus() {
        return nextFrame.getScoreOfFirstRoll();
    }

}
