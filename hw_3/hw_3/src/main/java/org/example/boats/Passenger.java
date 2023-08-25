package org.example.boats;

import java.util.Random;

public class Passenger {
    private static final Random random = new Random();
    private static final int MIN_BOARDING_DURATION = 5;
    private static final int MAX_BOARDING_DURATION = 20;
    private int boardingDuration;

    public Passenger() {
        this.boardingDuration = MIN_BOARDING_DURATION + random.nextInt(MAX_BOARDING_DURATION - MIN_BOARDING_DURATION + 1);
    }

    public int getBoardingDuration() {
        return boardingDuration;
    }
}
