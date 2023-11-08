package com.example.poemapp.models.chat;

import lombok.Data;

import java.util.UUID;

@Data
public class ChatMessage {
    public String content;
    public String sender;
    public UUID jobId;
}
