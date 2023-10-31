package com.example.poemapp.jobs;

import com.example.poemapp.services.AzureVisionService;

import java.util.Date;

public class AzureJob implements Runnable{
    private final AzureVisionService azureVisionService;
    private final String filePath;

    public AzureJob(
            AzureVisionService azureVisionService,
            String filePath
    ){
        this.azureVisionService=azureVisionService;
        this.filePath=filePath;

    }
    @Override
    public void run() {
        try{
            Thread.sleep(30000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        //String sendAt=(new Date()).toString();
        azureVisionService.analyzeImageFromFile(filePath);

    }
}
