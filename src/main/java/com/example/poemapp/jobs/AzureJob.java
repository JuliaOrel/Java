package com.example.poemapp.jobs;

import com.example.poemapp.models.chat.ChatMessage;
import com.example.poemapp.services.AzureVisionService;
import com.example.poemapp.services.ChatGPTJobService;
import com.example.poemapp.services.ChatGPTService;
import com.example.poemapp.services.WebSocketService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;

import java.util.UUID;

public class AzureJob implements Runnable{
    private final AzureVisionService azureVisionService;
    private final ChatGPTJobService chatGPTJobService;
    private final String filePath;
    private UUID jobId;
    WebSocketService webSocketService;

    public AzureJob(
            AzureVisionService azureVisionService,
            ChatGPTJobService chatGPTJobService, WebSocketService webSocketService,
            String filePath, UUID jobId
    ){
        this.azureVisionService=azureVisionService;
        this.chatGPTJobService=chatGPTJobService;
        this.webSocketService=webSocketService;
        this.filePath=filePath;
        this.jobId=jobId;

    }
    @Override
    public void run() {
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        String question=azureVisionService.analyzeImageFromFile(this.filePath);
        System.out.println(question);
        chatGPTJobService.pushGptJob(question, this.jobId);
        System.out.println("AzureJob № "+this.jobId+" was executed");
        ChatMessage mes=new ChatMessage();
        mes.sender="AzureJob";
        mes.content=" № "+this.jobId+" was executed";
        mes.jobId=this.jobId;

        webSocketService.sendMessageAboutJob(jobId, mes);
        //webSocketService.sendMessageToTopic(mes);

    }
}
