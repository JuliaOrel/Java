package org.example.threads;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ThreadThird implements Runnable{
    private final Semaphore semaphore = new Semaphore(0);

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите путь к файлу: ");
        String filePath = null;
        try {
            filePath = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File inputFile = new File(filePath);
        File primesFile = new File("src/main/dir_2/primes.txt");
        File factorialsFile = new File("src/main/dir_2/factorials.txt");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Integer> numbers = new ArrayList<>();

        Thread fillThread = new Thread(() -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
                for (int i = 0; i < 10; i++) {
                    int randomNumber = (int) (Math.random() * 25);
                    numbers.add(randomNumber);
                    writer.write(randomNumber + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            semaphore.release();
        });

        Thread primesThread = new Thread(() -> {
            try {
                semaphore.acquire();
                try (BufferedReader reader1 = new BufferedReader(new FileReader(inputFile));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(primesFile))) {
                    String line;
                    while ((line = reader1.readLine()) != null) {
                        int number = Integer.parseInt(line);
                        if (isPrime(number)) {
                            writer.write(number + "\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        });

        Thread factorialsThread = new Thread(() -> {
            try {
                semaphore.acquire();
                try (BufferedReader reader1 = new BufferedReader(new FileReader(inputFile));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(factorialsFile))) {
                    String line;
                    while ((line = reader1.readLine()) != null) {
                        int number = Integer.parseInt(line);
                        long factorial = calculateFactorial(number);
                        writer.write(factorial + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        });

        fillThread.start();
        primesThread.start();
        factorialsThread.start();

        try {
            fillThread.join();
            primesThread.join();
            factorialsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        System.out.println("Статистика:");
        System.out.println("Количество сгенерированных чисел: " + numbers.size());
        System.out.println("Количество найденных простых чисел: " + countLines(primesFile));
        System.out.println("Количество вычисленных факториалов: " + countLines(factorialsFile));
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static long calculateFactorial(int number) {
        long factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }
    private static int countLines(File file) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
