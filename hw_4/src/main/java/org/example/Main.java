package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        PiCalculation calc=new PiCalculation();
//        calc.run();

        //Весь код в main, не смотри в PiCalculation and PiCalculator
        int iterationCount = 100000000;
        int threadCount = 4;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        AtomicReference<Double> pi = new AtomicReference<>(0.0);

        for (int i = 0; i < threadCount; i++) {
            int start = i * iterationCount / threadCount;
            int end = (i + 1) * iterationCount / threadCount;
            executor.submit(() -> {
                double localSum = IntStream.range(start, end).mapToDouble(j -> (j + 0.5) / iterationCount).map(x -> 4.0 / (1.0 + x * x)).sum();
                pi.updateAndGet(v -> v + localSum);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        double result = pi.get() / iterationCount;
        System.out.println(result);
    }

}


