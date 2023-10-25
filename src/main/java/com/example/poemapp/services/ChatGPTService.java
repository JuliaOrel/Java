package com.example.poemapp.services;

import com.example.poemapp.entities.ChatGptRequest;
import com.example.poemapp.entities.ChatMessage;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

@Service
public class ChatGPTService {
    @Autowired
    AlarmService alarmService;


    @Value("${OPENAI_API_KEY}")
    String apiKey;
    public String SendQuestion (String question ) {
        String res = "";

        HttpClient httpclient = HttpClients.createDefault();

        try {
            // Создание HTTP POST-запроса
            HttpPost request = new HttpPost("https://api.openai.com/v1/chat/completions");

            // Установка заголовков для аутентификации
            request.setHeader("Authorization", "Bearer " + apiKey);
            request.setHeader("Content-Type", "application/json");

            // Создание JSON-запроса
            ChatGptRequest chatGptRequest = new ChatGptRequest();
            chatGptRequest.model = "gpt-3.5-turbo";
            ChatMessage message = new ChatMessage();
            message.role = "user";
            message.content = question;
            chatGptRequest.messages = new ArrayList<>();
            chatGptRequest.messages.add(message);
            chatGptRequest.temperature = 0.7;
            Gson gson = new Gson();
            String jsonRequest = gson.toJson(chatGptRequest);
//            String jsonRequest = "{\n" +
//                    "     \"model\": \"gpt-3.5-turbo\",\n" +
//                    "     \"messages\": [{\"role\": \"user\", \"content\": \"" + question + "\"}],\n" +
//                    "     \"temperature\": 0.7\n" +
//                    "   }";
            System.out.println("Я задаю вопрос: " + jsonRequest);

            // Установка JSON-запроса в теле запроса
            request.setEntity(new StringEntity(jsonRequest));

            // Выполнение запроса
            HttpResponse response = httpclient.execute(request);

            // Обработка ответа
            String jsonResponse = EntityUtils.toString(response.getEntity());
            System.out.println("Ответ на вопрос: " + jsonResponse);
            // Обработать ответ от чатика
            // если ошибка - ТО - alarmService.alarm
            res = jsonResponse;
        } catch (Exception e) {
            alarmService.alarm(e.getMessage());
            e.printStackTrace();
        }


        return res;
    }
}
