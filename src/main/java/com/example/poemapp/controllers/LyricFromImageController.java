package com.example.poemapp.controllers;

import com.example.poemapp.jobs.AzureJob;
import com.example.poemapp.services.AzureJobService;
import com.example.poemapp.services.AzureVisionService;
import com.example.poemapp.services.ChatGPTService;
import com.example.poemapp.services.LocalFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;



@RestController
public class LyricFromImageController {
    @Autowired
    private LocalFileService localFileService;
    @Autowired
    private AzureVisionService azureVisionService;
    @Autowired
    private AzureJobService azureJobService;
    @Autowired
    private ChatGPTService chatGPTService;

//    @GetMapping("/")
//    public String index() {
//        return ("pages/index");
//    }
    @PostMapping("/upload")
    public String upload(
            @RequestParam("file") MultipartFile file
    ) {
        // Проверяем - а прислал ли нам пользователь файл вообще
        if (file.isEmpty()) {
            return " No File in Request";
        }

        String filePath = localFileService.uploadFile("ai", file);
        String question = azureVisionService.analyzeImageFromFile(filePath);
        String lyric = chatGPTService.SendQuestion(question + "\n на русском языке");
        return lyric;
    }
    @PostMapping("/job")
    public String job(@RequestParam("file") MultipartFile file) throws ExecutionException, InterruptedException {
        String filePath = localFileService.uploadFile("ai", file);

        UUID id=UUID.randomUUID();
        azureJobService.pushAzureJob(filePath, id);

        return "Task number "+id+" was created";
    }
}
