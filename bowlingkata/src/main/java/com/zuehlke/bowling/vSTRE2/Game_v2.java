package com.zuehlke.bowling.vSTRE2;

import java.util.ArrayList;
import java.util.List;

// this is a really bad solution but requires only little code
// for each score-step we require the sum of the frame and the size of the frame (-> combined into List<List<int>> frame)
public class Game_v2 {

    private List<Integer> rolls = new ArrayList<>();

    public int score() {
        int score = 0;
        final List<List<Integer>> frames = new ArrayList<>();
        List<Integer> currentFrame = new ArrayList<>();
        int indexOfRoll = 0;
        while (!rolls.isEmpty() && frames.size() < 10) {
            int scoreOfRoll = rolls.get(indexOfRoll);
            score += scoreOfRoll;
            currentFrame.add(scoreOfRoll);
            if (scoreOfRoll == 10) {
                score += rolls.get(indexOfRoll+1) + rolls.get(indexOfRoll+2);
                frames.add(currentFrame);
                currentFrame = new ArrayList<>();
            } else if (currentFrame.stream().reduce(0, (each,sum) -> each + sum) == 10) {
                score += rolls.get(indexOfRoll+1);
                frames.add(currentFrame);
                currentFrame = new ArrayList<>();
            } else if (currentFrame.size() == 2) {
                frames.add(currentFrame);
                currentFrame = new ArrayList<>();
            }
            indexOfRoll++;
        }
        return score;
    }

    public void roll(int numberOfPins) {
        rolls.add(numberOfPins);
    }

}
