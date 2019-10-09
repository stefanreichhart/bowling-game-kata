package com.zuehlke.bowling.step5;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int MAX_FRAMES = 10;

    private final List<Frame> frames;
    private Frame currentFrame;

    public Game() {
        frames = new ArrayList<>();
        currentFrame = new Frame();
        frames.add(currentFrame);
    }

    public int score() {
        int score = 0;
        for (int i = 0; i < Math.min(frames.size(), MAX_FRAMES); i++) {
            final Frame frame = frames.get(i);
            score += frame.getScore();
            if (frame.isStrike())  {
                score += getScoreForStrikeBonus(i);
            }
        }
        return score;
    }

    private int getScoreForStrikeBonus(int index) {
        final Frame nextFrame = frames.get(index + 1);
        int score = nextFrame.getScore();
        if (nextFrame.isStrike()) {
            score += frames.get(index + 2).getScoreOfFirstRoll();
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
