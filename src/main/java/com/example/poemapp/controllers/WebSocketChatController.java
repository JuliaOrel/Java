package com.example.poemapp.controllers;


import com.example.poemapp.models.chat.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

public class WebSocketChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage send (ChatMessage message) {

        return message;
    }
}
