package com.zuehlke.bowling.step3;

public class Game {

    private int score;

    public int score() {
        return score;
    }

    public void roll(int numberOfPins) {
        score += numberOfPins;
    }

}
