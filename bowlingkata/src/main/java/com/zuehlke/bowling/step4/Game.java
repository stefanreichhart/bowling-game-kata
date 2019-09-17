package com.zuehlke.bowling.step4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {

    private List<Integer> rolls = new ArrayList<>();

    public int score() {
        int score = 0;
        int currentRoll = 0;
        int currentFrame = 0;
        while (currentFrame < 10 && !rolls.isEmpty()) {
            int frameScore = rolls.get(currentRoll);
            if (frameScore == 10) {
                frameScore += rolls.get(currentRoll+1) + rolls.get(currentRoll+2);
                currentFrame++;
            } else {
                currentFrame += currentRoll % 2;
            }
            score += frameScore;
            currentRoll++;
        }
        return score;
    }

    public void roll(int numberOfPins) {
        Objects.requireNonNull(numberOfPins);
        rolls.add(numberOfPins);
    }

}
