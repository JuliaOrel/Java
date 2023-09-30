package com.itstep.hello_spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final String monoBankApiUrl = "https://api.monobank.ua/bank/currency";

    @GetMapping("/exchange")
    public ResponseEntity<String> getCurrencyExchange() {
        // Создаем объект RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Выполняем GET-запрос и получаем ответ в виде строки
        ResponseEntity<String> response = restTemplate.getForEntity(monoBankApiUrl, String.class);

        // Возвращаем ответ от стороннего сервиса
        return response;
    }
}
