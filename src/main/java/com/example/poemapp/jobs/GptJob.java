package com.example.poemapp.jobs;

import com.example.poemapp.models.chat.ChatMessage;
import com.example.poemapp.services.AzureVisionService;
import com.example.poemapp.services.ChatGPTService;
import com.example.poemapp.services.WebSocketService;

import java.util.UUID;

public class GptJob implements Runnable{
    private final ChatGPTService chatGPTService;
    private final String question;
    private UUID jobId;
    private WebSocketService webSocketService;
    public GptJob(
            ChatGPTService chatGPTService,
            String question, WebSocketService webSocketService, UUID jobId
    ){
        this.chatGPTService=chatGPTService;
        this.question=question;
        this.webSocketService=webSocketService;
        this.jobId=jobId;

    }
    @Override
    public void run() {
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        String poem=chatGPTService.SendQuestion(this.question);
        //сокет-соединение: возвращаю стих
        System.out.println("GPTJob № "+this.jobId+" was executed"+poem);
        ChatMessage mes=new ChatMessage();
        mes.sender="GPTJob";
        mes.content=" № "+this.jobId+" was executed: "+poem;
        mes.jobId=this.jobId;

        webSocketService.sendMessageAboutJob(jobId, mes);
    }
}
