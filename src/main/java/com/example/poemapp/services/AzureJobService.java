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
    private final ChatGPTService chatGPTService;

    public AzureJobService (AzureVisionService azureVisionService, ChatGPTService chatGPTService) {
        this.azureVisionService=azureVisionService;
        this.chatGPTService=chatGPTService;
    }

    @Async("azureExecutor")
    public Future<UUID> pushAzureJob(String filePath){
        UUID id=UUID.randomUUID();
        AzureJob job= new AzureJob(azureVisionService, chatGPTService,filePath,id);
        job.run();
        return new AsyncResult<>(id);
    }
}
