package org.example.threads;

import java.util.Random;

public class ThreadFirst implements Runnable{
    @Override
    public void run() {
        int[] array = new int[50];
        Random random = new Random();

        Thread fillThread = new Thread(() -> {
            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(100);
            }
            System.out.println("Array filled.");
        });

        Thread sumThread = new Thread(() -> {
            try {
                fillThread.join();
                int sum = 0;
                for (int num : array) {
                    sum += num;
                }
                System.out.println("Sum: " + sum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread avgThread = new Thread(() -> {
            try {
                fillThread.join();
                int sum = 0;
                for (int num : array) {
                    sum += num;
                }
                double avg = (double) sum / array.length;
                System.out.println("Average: " + avg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        fillThread.start();
        sumThread.start();
        avgThread.start();

        try {
            fillThread.join();
            sumThread.join();
            avgThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Array: " + java.util.Arrays.toString(array));


    }


}
