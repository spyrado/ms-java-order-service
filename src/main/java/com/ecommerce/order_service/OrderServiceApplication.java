package com.ecommerce.order_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@SpringBootApplication
public class OrderServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(OrderServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
		System.out.println(">>> teste!");
		ZonedDateTime agora = ZonedDateTime.now(ZoneOffset.UTC);
		log.info("Data atual: {}", agora);
	}

}
