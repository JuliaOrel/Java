package org.example.threads.entities;

import java.io.*;
import java.util.List;

public class SearchThread implements Runnable{
    private List<String> fileList;
    private String searchWord;
    public static int countSearchWords;
    private StringBuilder mergedContent = new StringBuilder();

    public SearchThread(List<String> fileList, String searchWord) {
        this.fileList = fileList;
        this.searchWord = searchWord;
    }
    @Override
    public void run() {
        // Поиск файлов и объединение содержимого
        for (String filePath : fileList) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(searchWord)) {
                        countSearchWords++;
                        mergedContent.append(line).append("\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Сохранение объединенного содержимого в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("merged_file.txt"))) {
            writer.write(mergedContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

