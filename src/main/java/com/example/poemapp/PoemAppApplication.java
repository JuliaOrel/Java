package com.example.poemapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PoemAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoemAppApplication.class, args);
	}

}
