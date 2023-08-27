package org.example.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PiCalculation implements Runnable{
    @Override
    public void run() {
        int iterationCount = 100000000; // Количество итераций
        int threadCount = 6; // Количество потоков

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<Double>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            futures.add(executor.submit(new PiCalculator(iterationCount / threadCount)));
        }

        double totalSum = 0.0;
        for (Future<Double> future : futures) {
            try {
                totalSum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        double pi = totalSum * 2;
        System.out.println("Approximate value of Pi: " + pi);
    }
}
