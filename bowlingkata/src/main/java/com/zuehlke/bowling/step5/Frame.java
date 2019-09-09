package com.zuehlke.bowling.step5;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private List<Integer> rolls = new ArrayList<>();

    public int size() {
        return rolls.size();
    }

    public int getScoreAtRoll(int index) {
        return rolls.get(index);
    }

    public void roll(int numberOfPins) {
        rolls.add(numberOfPins);
    }

}
