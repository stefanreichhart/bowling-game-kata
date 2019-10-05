package com.zuehlke.playground.step2;

public class Game {

    private int score;

    public int score() {
        return score;
    }

    public void roll(int numberOfPins) {
        score += numberOfPins;
    }

}
