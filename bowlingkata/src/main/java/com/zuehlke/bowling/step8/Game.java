package com.zuehlke.bowling.step8;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int MAX_FRAMES = 10;

    private List<Frame> frames;

    public Game() {
        initGame();
    }

    private void initGame() {
        frames = new ArrayList<>();
        frames.add(new Frame());
    }

    public int score() {
        int score = 0;
        for (int i = 0; i < getMaxNumberOfFrames(); i++) {
            score += frames.get(i).getScore();
        }
        return score;
    }

    public void roll(int numberOfPins) {
        final Frame currentFrame = getCurrentFrame();
        currentFrame.roll(numberOfPins);
        if (currentFrame.isComplete()) {
            addNewFrame(currentFrame);
        }
    }

    private int getMaxNumberOfFrames() {
        return Math.min(frames.size(), MAX_FRAMES);
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    private void addNewFrame(Frame currentFrame) {
        final Frame nextFrame = new Frame();
        currentFrame.setNextFrame(nextFrame);
        frames.add(nextFrame);
    }

}
