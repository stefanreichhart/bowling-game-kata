package com.zuehlke.bowling.step5_lists;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int MAX_FRAMES = 10;

    private List<List<Integer>> frames;
    private List<Integer> currentFrame;

    public Game() {
        frames = new ArrayList<>();
        currentFrame = new ArrayList<>();
        frames.add(currentFrame);
    }

    public int score() {
        int score = 0;
        for (int i = 0; i < Math.min(frames.size(), MAX_FRAMES); i++) {
            final List<Integer> frame = frames.get(i);
            for (Integer scoreAtRoll : frame) {
                score += scoreAtRoll;
                if (isStrike(scoreAtRoll)) {
                    score += getScoreForStrikeBonus(i);
                }
            }
        }
        return score;
    }

    private boolean isStrike(Integer scoreAtRoll) {
        return scoreAtRoll == 10;
    }

    private int getScoreForStrikeBonus(int index) {
        int score = 0;
        List<Integer> nextFrame = frames.get(index + 1);
        if (nextFrame.size() == 2) {
            score += nextFrame.get(0);
            score += nextFrame.get(1);
        } else if (nextFrame.size() == 1) {
            score += nextFrame.get(0);
            score += frames.get(index + 2).get(0);
        } else {
            throw new IllegalStateException("This is not a Strike");
        }
        return score;
    }

    public void roll(int numberOfPins) {
        currentFrame.add(numberOfPins);
        if (currentFrame.size() >= 2 || isStrike(numberOfPins)) {
            currentFrame = new ArrayList<>();
            frames.add(currentFrame);
        }
    }

}
