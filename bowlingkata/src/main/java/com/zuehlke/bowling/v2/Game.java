package com.zuehlke.bowling.v2;

public class Game {

    private int score;

    public void roll(int numberOfPins) {
        this.score += numberOfPins;
    }

    public int score() {
        return score;
    }
}
