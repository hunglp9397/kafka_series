package com.hunglp.orderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OrderServiceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(OrderServiceApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
		LOG.info("App started!");
	}

}
