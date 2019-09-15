package com.zuehlke.bowling.step3;

import java.util.ArrayList;
import java.util.Objects;

public class Game {

    private ArrayList<Integer> rolls = new ArrayList<>();

    public int score() {
        return rolls.stream()
                .mapToInt(each -> each)
                .sum();
    }

    public void roll(int numberOfPins) {
        Objects.requireNonNull(numberOfPins);
        rolls.add(numberOfPins);
    }

}
