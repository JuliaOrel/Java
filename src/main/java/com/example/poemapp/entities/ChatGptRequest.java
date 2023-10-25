package com.example.poemapp.entities;

import java.util.List;

public class ChatGptRequest {
    public String model;
    public List<ChatMessage> messages;
    public double temperature;
}
