package com.example.poemapp.controllers;

import com.example.poemapp.models.chat.ChatMessage;
import com.example.poemapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.ExecutionException;


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
    @Autowired
    private WebSocketService webSocketService;

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
    public UUID job(@RequestParam("file") MultipartFile file) throws ExecutionException, InterruptedException {
        String filePath = localFileService.uploadFile("ai", file);

        UUID jobId=UUID.randomUUID();
        //UUID jobId= UUID.fromString("b24d1b0e-a6ba-439e-98e4-5ac71afd9c78");
        azureJobService.pushAzureJob(filePath, jobId);

        return jobId;
    }
}
