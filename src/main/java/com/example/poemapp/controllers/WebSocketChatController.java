package com.example.poemapp.controllers;


import com.example.poemapp.models.chat.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage send (ChatMessage message) {
        System.out.println(message);
        return message;
    }
}
