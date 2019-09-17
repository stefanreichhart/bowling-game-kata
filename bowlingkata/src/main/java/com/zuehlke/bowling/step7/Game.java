package com.zuehlke.bowling.step7;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Frame> frames;
    private Frame currentFrame;

    public Game() {
        frames = new ArrayList<>();
        currentFrame = new Frame();
        frames.add(currentFrame);
    }

    public int score() {
        int score = 0;
        for (int i = 0; i < Math.min(frames.size(), 10); i++) {
            int frameScore = 0;
            final Frame frame = frames.get(i);
            for (int j = 0; j < frame.size(); j++) {
                Integer scoreAtRoll = frame.getScoreAtRoll(j);
                frameScore += scoreAtRoll;
                if (isStrike(scoreAtRoll)) {
                    frameScore += getScoreOfStrikeBonus(i);
                }
                if (isSpare(frameScore, j)) {
                    frameScore += getScoreOfSpareBonus(i);
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
        return frames.get(index + 1).getScoreAtRoll(0);
    }

    private int getScoreOfStrikeBonus(int i) {
        int score = 0;
        Frame nextFrame = frames.get(i + 1);
        if (nextFrame.size() == 2) {
            score += nextFrame.getScoreAtRoll(0);
            score += nextFrame.getScoreAtRoll(1);
        } else if (nextFrame.size() == 1) {
            score += nextFrame.getScoreAtRoll(0);
            score += frames.get(i + 2).getScoreAtRoll(0);
        } else {
            throw new IllegalStateException("This is not a Strike");
        }
        return score;
    }

    public void roll(int numberOfPins) {
        currentFrame.roll(numberOfPins);
        if (currentFrame.size() >= 2 || isStrike(numberOfPins)) {
            currentFrame = new Frame();
            frames.add(currentFrame);
        }
    }

}
