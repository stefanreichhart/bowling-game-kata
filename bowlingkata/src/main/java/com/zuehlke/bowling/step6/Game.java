package com.zuehlke.bowling.step6;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<List<Integer>> frames;
    private List<Integer> currentFrame;

    public Game() {
        frames = new ArrayList<>();
        currentFrame = new ArrayList<>();
        frames.add(currentFrame);
    }

    public int score() {
        int score = 0;
        for (int frameIndex = 0; frameIndex < Math.min(frames.size(), 10); frameIndex++) {
            int frameScore = 0;
            List<Integer> frame = frames.get(frameIndex);
            for (int rollIndex = 0; rollIndex < frame.size(); rollIndex++) {
                Integer scoreAtRoll = frame.get(rollIndex);
                frameScore += scoreAtRoll;
                if (isStrike(scoreAtRoll)) {
                    frameScore += getScoreOfStrikeBonus(frameIndex);
                }
                if (isSpare(frameScore, rollIndex)) {
                    frameScore += getScoreOfSpareBonus(frameIndex);
                }
            }
            score += frameScore;
        }
        return score;
    }

    private boolean isSpare(int frameScore, int numberOfRollsInFrame) {
        return numberOfRollsInFrame == 1 && frameScore == 10;
    }

    private boolean isStrike(Integer scoreAtRoll) {
        return scoreAtRoll == 10;
    }

    private int getScoreOfSpareBonus(int index) {
        return frames.get(index+1).get(0);
    }

    private int getScoreOfStrikeBonus(int i) {
        int score = 0;
        List<Integer> nextFrame = frames.get(i+1);
        if (nextFrame.size() == 2) {
            score += nextFrame.get(0);
            score += nextFrame.get(1);
        } else if (nextFrame.size() == 1) {
            score += nextFrame.get(0);
            score += frames.get(i+2).get(0);
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
