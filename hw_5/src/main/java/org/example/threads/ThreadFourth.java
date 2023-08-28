package org.example.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFourth implements Runnable{
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Введите путь к исходной директории: ");
            String sourcePath = reader.readLine();
            System.out.print("Введите путь к новой директории: ");
            String destinationPath = reader.readLine();

            Path sourceDirectory = Path.of(sourcePath);
            Path destinationDirectory = Path.of(destinationPath);

            AtomicInteger fileCount = new AtomicInteger(0);
            AtomicInteger directoryCount = new AtomicInteger(0);

            copyDirectory(sourceDirectory, destinationDirectory, fileCount, directoryCount);

            System.out.println("Скопировано файлов: " + fileCount.get());
            System.out.println("Создано директорий: " + directoryCount.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyDirectory(Path source, Path destination, AtomicInteger fileCount, AtomicInteger directoryCount) throws IOException {
        if (!Files.exists(destination)) {
            Files.createDirectories(destination);
            directoryCount.incrementAndGet();
        }
        if (Files.isDirectory(source) && Files.list(source).count() == 0) {
            directoryCount.incrementAndGet();
            return;
        }
        Files.walk(source).forEach(sourcePath -> {
            try {
                Path destinationPath = destination.resolve(source.relativize(sourcePath));
                if (Files.isDirectory(sourcePath)) {
                    if (!Files.exists(destinationPath)) {
                        Files.createDirectory(destinationPath);
                        directoryCount.incrementAndGet();
                    }
                } else {
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    fileCount.incrementAndGet();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
