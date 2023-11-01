package com.example.poemapp.jobs;

import com.example.poemapp.services.AzureVisionService;
import com.example.poemapp.services.ChatGPTService;

import java.util.UUID;

public class GptJob implements Runnable{
    private final ChatGPTService chatGPTService;
    private final String question;
    private UUID id;
    public GptJob(
            ChatGPTService chatGPTService,
            String question, UUID id
    ){
        this.chatGPTService=chatGPTService;
        this.question=question;
        this.id=id;

    }
    @Override
    public void run() {
        try{
            Thread.sleep(30000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        chatGPTService.SendQuestion(this.question);
        System.out.println("Job № "+this.id+" was executed");
    }
}
