package com.example.poemapp.jobs;

import com.example.poemapp.services.AzureVisionService;
import com.example.poemapp.services.ChatGPTJobService;
import com.example.poemapp.services.ChatGPTService;

import java.util.UUID;

public class AzureJob implements Runnable{
    private final AzureVisionService azureVisionService;
    private final ChatGPTService chatGPTService;
    private final String filePath;
    private UUID id;

    public AzureJob(
            AzureVisionService azureVisionService,
            ChatGPTService chatGPTService,
            String filePath, UUID id
    ){
        this.azureVisionService=azureVisionService;
        this.chatGPTService=chatGPTService;
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
        azureVisionService.analyzeImageFromFile(this.filePath);
        //chatGPTService.SendQuestion();
        System.out.println("Job № "+this.id+" was executed");

    }
}
