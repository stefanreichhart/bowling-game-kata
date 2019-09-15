package com.zuehlke.bowling.step8;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    public static final int MAX_SCORE = 10;
    public static final int MAX_ROLLS = 2;

    private Frame nextFrame;
    private List<Integer> rolls = new ArrayList<>();

    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    public void roll(int numberOfPins) {
        rolls.add(numberOfPins);
    }

    private int getScore() {
        int score = 0;
        for (int i = 0; i < rolls.size(); i++) {
            score += rolls.get(i);
        }
        return score;
    }

    private int getScoreForSpareBonus() {
        return rolls.get(0);
    }

    public boolean isComplete() {
        return isStrike() || isSpare() || rolls.size() == MAX_ROLLS;
    }

    private boolean isStrike() {
        return rolls.size() == 1 && rolls.get(0) == MAX_SCORE;
    }

    private boolean isSpare() {
        return rolls.size() == MAX_ROLLS && getScore() == MAX_SCORE;
    }

    public int getScoreOfFrame() {
        int score = getScore();
        if (isStrike())  {
            score += nextFrame.getScore();
            if (nextFrame.isStrike()) {
                score += nextFrame.nextFrame.getScoreForSpareBonus();
            }
        }
        if (isSpare()) {
            score += nextFrame.getScoreForSpareBonus();
        }
        return score;
    }

}
