package com.zuehlke.playground.vSTRE2;

import java.util.ArrayList;
import java.util.List;

// this is a really bad solution but requires only little code
// for each score-step we require the sum of the frame and the size of the frame (-> combined into List<int> currentFrame)
public class Game_v1 {

    private List<Integer> rolls = new ArrayList<>();

    public int score() {
        int score = 0;
        List<Integer> currentFrame = new ArrayList<>();
        int numberOfFrames = 0;
        int indexOfRoll = 0;
        while (!rolls.isEmpty() && numberOfFrames < 10) {
            int scoreOfRoll = rolls.get(indexOfRoll);
            score += scoreOfRoll;
            currentFrame.add(scoreOfRoll);
            if (scoreOfRoll == 10) {
                score += rolls.get(indexOfRoll+1) + rolls.get(indexOfRoll+2);
                currentFrame = new ArrayList<>();
                numberOfFrames++;
            } else if (currentFrame.stream().reduce(0, (each,sum) -> each + sum) == 10) {
                score += rolls.get(indexOfRoll+1);
                currentFrame = new ArrayList<>();
                numberOfFrames++;
            } else if (currentFrame.size() == 2) {
                currentFrame = new ArrayList<>();
                numberOfFrames++;
            }
            indexOfRoll++;
        }
        return score;
    }

    public void roll(int numberOfPins) {
        rolls.add(numberOfPins);
    }

}
