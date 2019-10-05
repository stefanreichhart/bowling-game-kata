package com.zuehlke.playground.vSTRE;

import java.util.ArrayList;
import java.util.List;

/* stefan's playground for an solution */
public class Game {

    private static final int MAX_NUMBER_OF_REGULAR_FRAMES = 10;
    private static final int MAX_NUMBER_OF_FRAMES_INCLUDING_BONUS_FRAME = 11;

    private List<Frame> frames = new ArrayList<>();

    public void roll(int numberOfPins) {
        Frame lastFrame = getLastFrame();
        if (lastFrame.isComplete()) {
            if (frames.size() <= MAX_NUMBER_OF_REGULAR_FRAMES) {
                lastFrame = new Frame();
                frames.add(lastFrame);
            } else {
                throw new IllegalStateException("The Game is already finished");
            }
        }
        if (frames.size() == MAX_NUMBER_OF_FRAMES_INCLUDING_BONUS_FRAME) {
            if (frames.get(frames.size()-2).isSpare() && lastFrame.rolls() == 1) {
                throw new IllegalStateException("The Game is already finished");
            }
        }
        lastFrame.roll(numberOfPins);
    }

    private Frame getLastFrame() {
        if (frames.isEmpty()) {
            frames.add(new Frame());
        }
        return frames.get(frames.size()-1);
    }

    public int score() {
        int score = 0;
        for (int currentFrameIndex = 0; currentFrameIndex < Math.min(10, frames.size()); currentFrameIndex++) {
            score += getScore(currentFrameIndex);
        }
        return score;
    }

    private int getScore(int currentFrameIndex) {
        final Frame currentFrame = frames.get(currentFrameIndex);
        return currentFrame.score() + getBonusScore(currentFrameIndex, currentFrame);
    }

    private int getBonusScore(int currentFrameIndex, Frame currentFrame) {
        if (currentFrame.isStrike()) {
            final Frame nextFrame = frames.get(currentFrameIndex + 1);
            return nextFrame.score();
        } else if (currentFrame.isSpare()) {
            final Frame nextFrame = frames.get(currentFrameIndex + 1);
            return nextFrame.scoreOfFirstRoll();
        } else {
            return 0;
        }
    }

}
