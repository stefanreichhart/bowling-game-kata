package com.zuehlke.playground.step3;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Integer> rolls = new ArrayList<>();

    public int score() {
        return rolls.stream()
                .mapToInt(each -> each)
                .sum();
    }

    public void roll(int numberOfPins) {
        rolls.add(numberOfPins);
    }

}
