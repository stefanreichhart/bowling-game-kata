package com.zuehlke.bowling.step4;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<Integer> rolls = new ArrayList<>();

    public int score() {
        return rolls.stream()
                .mapToInt(each -> each)
                .sum();
    }

    public void roll(int numberOfPins) {
        rolls.add(numberOfPins);
    }

}
