package org.example.threads;

import org.example.threads.entities.CensorThread;
import org.example.threads.entities.SearchThread;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ThreadSecond implements Runnable{
    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Введите путь к директории: ");
            String directoryPath = reader.readLine();
            System.out.print("Введите слово для поиска: ");
            String searchWord = reader.readLine();

            //Получение списка файлов в указанной директории
            List<String> fileList = new ArrayList<>();
            File[] files = new File(directoryPath).listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileList.add(file.getAbsolutePath());
                    }
                }
            }

            // Создание и запуск первого потока
            Thread searchThread = new Thread(new SearchThread(fileList, searchWord));
            searchThread.start();

            // Ожидание завершения первого потока
            try {
                searchThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Создание и запуск второго потока
            Thread censorThread = new Thread(new CensorThread());
            censorThread.start();

            // Ожидание завершения второго потока
            try {
                censorThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Статистика: ..."); // Вывод статистики
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


