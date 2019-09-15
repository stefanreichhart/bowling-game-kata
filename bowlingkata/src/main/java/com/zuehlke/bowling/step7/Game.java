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
                if (scoreAtRoll == 10) {
                    frameScore += getScoreOfNext2Rolls(i);
                }
                if (j == 1 && frameScore == 10) {
                    frameScore += getScoreOfNextRoll(i);
                }
            }
            score += frameScore;
        }
        return score;
    }

    private int getScoreOfNextRoll(int index) {
        return frames.get(index + 1).getScoreAtRoll(0);
    }

    private int getScoreOfNext2Rolls(int i) {
        int score = 0;
        Frame nextFrame = frames.get(i + 1);
        if (nextFrame.size() == 2) {
            score += nextFrame.getScoreAtRoll(0);
            score += nextFrame.getScoreAtRoll(1);
        } else if (nextFrame.size() == 1) {
            score += nextFrame.getScoreAtRoll(0);
            score += frames.get(i + 2).getScoreAtRoll(0);
        } else {
            throw new IllegalStateException();
        }
        return score;
    }

    public void roll(int numberOfPins) {
        currentFrame.roll(numberOfPins);
        if (currentFrame.size() >= 2 || numberOfPins == 10) {
            currentFrame = new Frame();
            frames.add(currentFrame);
        }
    }

}
