package org.example.threads.entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CensorThread implements Runnable{
    private List<String> forbiddenWords = new ArrayList<>(); // Загрузите список запрещенных слов из файла
    public CensorThread() {
        loadForbiddenWords(); // Загрузка запрещенных слов при создании потока
    }

    private void loadForbiddenWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/res/forbidden_words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                forbiddenWords.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        // Обработка объединенного файла и удаление запрещенных слов
        try (BufferedReader reader = new BufferedReader(new FileReader("merged_file.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("censored_file.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String word : forbiddenWords) {
                    line = line.replace(word, "[censored]");
                }
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

