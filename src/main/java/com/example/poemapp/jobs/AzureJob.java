package com.example.poemapp.jobs;

import com.example.poemapp.services.AzureVisionService;
import com.example.poemapp.services.ChatGPTJobService;
import com.example.poemapp.services.ChatGPTService;

import java.util.UUID;

public class AzureJob implements Runnable{
    private final AzureVisionService azureVisionService;
    private final ChatGPTJobService chatGPTJobService;
    private final String filePath;
    private UUID id;

    public AzureJob(
            AzureVisionService azureVisionService,
            ChatGPTJobService chatGPTJobService,
            String filePath, UUID id
    ){
        this.azureVisionService=azureVisionService;
        this.chatGPTJobService=chatGPTJobService;
        this.filePath=filePath;
        this.id=id;

    }
    @Override
    public void run() {
        try{
            Thread.sleep(30000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        String question=azureVisionService.analyzeImageFromFile(this.filePath);
        System.out.println(question);
        chatGPTJobService.pushGptJob(question, this.id);
        System.out.println("AzureJob № "+this.id+" was executed");

    }
}
