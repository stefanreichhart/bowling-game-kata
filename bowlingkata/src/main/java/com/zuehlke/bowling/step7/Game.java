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
            final Frame frame = frames.get(i);
            score += frame.getScore();
            if (frame.isStrike())  {
                score += getScoreForStrikeBonus(i);
            }
            if (frame.isSpare()) {
                score += getScoreOfSpareBonus(i);
            }
        }
        return score;
    }

    private int getScoreForStrikeBonus(int index) {
        final Frame nextFrame = frames.get(index + 1);
        int score = nextFrame.getScore();
        if (nextFrame.isStrike()) {
            score += frames.get(index+2).getScoreForSpareBonus();
        }
        return score;
    }

    private int getScoreOfSpareBonus(int index) {
        final Frame nextFrame = frames.get(index + 1);
        return nextFrame.getScoreForSpareBonus();
    }

    public void roll(int numberOfPins) {
        currentFrame.roll(numberOfPins);
        if (currentFrame.isComplete()) {
            currentFrame = new Frame();
            frames.add(currentFrame);
        }
    }

}
