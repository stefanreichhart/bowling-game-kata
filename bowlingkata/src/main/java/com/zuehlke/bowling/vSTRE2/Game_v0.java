package com.zuehlke.bowling.vSTRE2;

import java.util.ArrayList;
import java.util.List;

// this is a really bad solution but requires only little code
// for each score-step we require the sum of the frame and the size of the frame (-> separate vars frameSize, scoreOfFrame)
public class Game_v0 {

    private List<Integer> rolls = new ArrayList<>();

    public int score() {
        int score = 0;
        int scoreOfFrame = 0;
        int frameSize = 0;
        int numberOfFrames = 1;
        int indexOfRoll = 0;
        while (!rolls.isEmpty() && numberOfFrames <= 10) {
            int scoreOfRoll = rolls.get(indexOfRoll);
            score += scoreOfRoll;
            scoreOfFrame += scoreOfRoll;
            if (scoreOfRoll == 10) {
                score += rolls.get(indexOfRoll+1) + rolls.get(indexOfRoll+2);
                frameSize = 2;
            } else if (scoreOfFrame == 10) {
                score += rolls.get(indexOfRoll+1);
                frameSize = 2;
            } else {
                frameSize++;
            }
            if (frameSize == 2) {
                frameSize = 0;
                scoreOfFrame = 0;
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
