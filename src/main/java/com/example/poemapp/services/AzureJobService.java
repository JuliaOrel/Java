package com.example.poemapp.services;

import com.example.poemapp.jobs.AzureJob;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AzureJobService {
    private AzureVisionService azureVisionService;

    public AzureJobService (AzureVisionService azureVisionService) {
        this.azureVisionService=azureVisionService;
    }

    @Async("azureExecutor")
    public void pushAzureJob(String filePath){
        AzureJob job= new AzureJob(azureVisionService, filePath);
        job.run();
    }
}
