package com.example.poemapp.controllers;

import com.example.poemapp.services.AzureVisionService;
import com.example.poemapp.services.LocalFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class LyricFromImageController {
    @Autowired
    private LocalFileService localFileService;
    @Autowired
    private AzureVisionService azureVisionService;

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
        //String lyric = chatGPTService.SendQuestion(question + "\n на русском языке");
        return question;
    }
}
