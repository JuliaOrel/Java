package org.example.boats;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Boat {
    private static final Random random = new Random();
    private static final int MIN_CAPACITY = 5;
    private static final int MAX_CAPACITY = 20;
    private int capacity;
    private Queue<Passenger> passengers;

    public Boat() {
        this.capacity = MIN_CAPACITY + random.nextInt(MAX_CAPACITY - MIN_CAPACITY + 1);
        this.passengers = new LinkedList<>();
    }

    public boolean isFull() {
        return passengers.size() >= capacity;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPassengerCount() {
        return passengers.size();
    }
}
