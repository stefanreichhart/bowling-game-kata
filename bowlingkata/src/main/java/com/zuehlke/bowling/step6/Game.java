package com.zuehlke.bowling.step6;

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
                final Frame nextFrame = frames.get(i + 1);
                score += nextFrame.getScore();
                if (nextFrame.isStrike()) {
                    score += frames.get(i+2).getScoreForSpareBonus();
                }
            }
            if (frame.isSpare()) {
                final Frame nextFrame = frames.get(i + 1);
                score += nextFrame.getScoreForSpareBonus();
            }
        }
        return score;
    }

    public void roll(int numberOfPins) {
        currentFrame.roll(numberOfPins);
        if (currentFrame.isComplete()) {
            currentFrame = new Frame();
            frames.add(currentFrame);
        }
    }

}
