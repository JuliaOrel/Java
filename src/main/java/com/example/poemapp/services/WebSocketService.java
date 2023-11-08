package com.example.poemapp.services;

import com.example.poemapp.models.chat.ChatMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;
    public WebSocketService(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate=messagingTemplate;
    }

    public  void sendMessageToTopic(ChatMessage message){
        messagingTemplate.convertAndSend("/topic/messages", message);
    }

    public void sendMessageAboutJob(UUID jobId, ChatMessage message){
        messagingTemplate.convertAndSend("/topic/" + jobId, message);
        System.out.println(jobId);
        System.out.println(message);
    }

}
