package org.example.boats;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class BoatStation implements Runnable {
    private static final Random random = new Random();

    @Override
    public void run() {

        int simulationTime = 1000; // Продолжительность симуляции в минутах
        int avgPassengerArrivalTimeDay = 10; // Среднее время между появлениями пассажиров днем
        int avgPassengerArrivalTimeNight = 20; // Среднее время между появлениями пассажиров ночью
        int avgBoatArrivalTimeDay = 15; // Среднее время между появлениями катеров днем
        int avgBoatArrivalTimeNight = 30; // Среднее время между появлениями катеров ночью
        int maxPassengersAtStop = 5; // Максимальное количество пассажиров на остановке

        Queue<Passenger> passengerQueue = new LinkedList<>();
        Queue<Boat> boatQueue = new LinkedList<>();

        for (int currentTime = 0; currentTime < simulationTime; currentTime++) {
            boolean isDaytime = currentTime >= 360 && currentTime < 1200; // 6:00 - 20:00

            // Приход пассажиров на причал
            if (random.nextInt(100) < (isDaytime ? 100 / avgPassengerArrivalTimeDay : 100 / avgPassengerArrivalTimeNight)) {
                passengerQueue.add(new Passenger());
            }

            // Приход катеров на причал
            if (random.nextInt(100) < (isDaytime ? 100 / avgBoatArrivalTimeDay : 100 / avgBoatArrivalTimeNight)) {
                boatQueue.add(new Boat());
            }

            // Обработка посадки пассажиров на катеры
            while (!passengerQueue.isEmpty() && !boatQueue.isEmpty()) {
                Boat boat = boatQueue.poll();
                while (!boat.isFull() && !passengerQueue.isEmpty()) {
                    boat.addPassenger(passengerQueue.poll());
                }
                boatQueue.offer(boat);
            }
        }

        int passengersBoarded = 0;
        int passengersLeft = passengerQueue.size();

        for (Boat boat : boatQueue) {
            passengersBoarded += boat.getPassengerCount();
        }

        System.out.println("Статистика:");
        System.out.println("Пассажиров, севших на катера: " + passengersBoarded);
        System.out.println("Пассажиров, оставшихся на причале: " + passengersLeft);
    }
}

