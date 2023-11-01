package com.example.poemapp.services;

import com.example.poemapp.jobs.AzureJob;
import com.example.poemapp.jobs.GptJob;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.Future;

@Service
public class ChatGPTJobService {
    private ChatGPTService chatGPTService;
    public ChatGPTJobService (ChatGPTService chatGPTService) {
        this.chatGPTService=chatGPTService;
    }

    @Async("gptExecutor")
    public Future<UUID> pushGptJob(String question){
        UUID id=UUID.randomUUID();
        GptJob job= new GptJob(chatGPTService, question, id);
        job.run();
        return new AsyncResult<>(id);
    }
}
