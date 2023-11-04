package com.example.poemapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableAsync
@EnableWebSocket
public class PoemAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoemAppApplication.class, args);
	}

}
