package com.zuehlke.bowling.v3;

public class Game {

    private int score;

    public void roll(int numberOfPins) {
        this.score += numberOfPins;
    }

    public int score() {
        return score;
    }
}