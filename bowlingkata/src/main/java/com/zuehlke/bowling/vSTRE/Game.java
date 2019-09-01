package com.zuehlke.bowling.vSTRE;

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
        for (int i=0; i<Math.min(10,frames.size()); i++) {
            Frame currentFrame = frames.get(i);
            score += currentFrame.score();
            if (currentFrame.isStrike()) {
                score += frames.get(i+1).score();
            } else if (currentFrame.isSpare()) {
                score += frames.get(i+1).scoreOfFirstRoll();
            }
        }
        return score;
    }

}
