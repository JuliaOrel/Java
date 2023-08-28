package org.example.threads;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CalculatePiElementSemaphore implements Runnable{
    static Random rnd = new Random(); // для случайных чисел

    private final int from;
    private final int to;

    public double result = 0;

    public double getResult() {
        return result;
    }

    private final Semaphore semaphore;

    public CalculatePiElementSemaphore(int from, int to, Semaphore semaphore){
        this.from = from;
        this.to = to;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Start Pi \t" + this.hashCode() + " from: " + from + " to " + to);
        try {
            for (int i = 0; i < rnd.nextInt(5); i++) {
                Thread.sleep(rnd.nextInt(300) + 300);
            }
        } catch (InterruptedException e) {
            System.out.println(" Can't pause this thread: " + e.getMessage() );
        }
        System.out.println("Finish Pi \t" + this.hashCode() + " from: " + from + " to " + to);
        semaphore.release();
    }
}
