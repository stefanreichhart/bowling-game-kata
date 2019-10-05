package com.zuehlke.playground.vSTRE2;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private List<Integer> rolls = new ArrayList<>();

    public void roll(int numberOfPins) {
        rolls.add(numberOfPins);
    }

}
