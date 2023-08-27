package org.example.pi;

import java.util.concurrent.Callable;

public class PiCalculator implements Callable<Double> {
    private final long iterations;

    public PiCalculator(long iterations) {
        this.iterations=iterations;
    }

    @Override
    public Double call() throws Exception {
        double sum = 0.0;
        for (long i = 0; i < iterations; i++) {
            double x = (double) i / iterations;
            double term = 4 / (1 + x * x);
            sum += term;
        }
        return sum;
    }
}
