package com.example.poemapp.services;

import com.example.poemapp.jobs.AzureJob;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.Future;

@Service
public class AzureJobService {
    private AzureVisionService azureVisionService;
    private final ChatGPTJobService chatGPTJobService;

    public AzureJobService (AzureVisionService azureVisionService, ChatGPTJobService chatGPTJobService) {
        this.azureVisionService=azureVisionService;
        this.chatGPTJobService=chatGPTJobService;
    }

    @Async("azureExecutor")
    public void pushAzureJob(String filePath, UUID id){

        AzureJob job= new AzureJob(azureVisionService, chatGPTJobService,filePath,id);
        //System.out.println("hello");
        job.run();
        //return new AsyncResult<>(id);
    }
}
