package com.poc.websocket.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebsocketServerPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketServerPocApplication.class, args);
	}
}
