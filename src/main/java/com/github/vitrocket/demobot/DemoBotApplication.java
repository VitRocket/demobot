package com.github.vitrocket.demobot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@Slf4j
@SpringBootApplication
public class DemoBotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(DemoBotApplication.class, args);
	}
}
