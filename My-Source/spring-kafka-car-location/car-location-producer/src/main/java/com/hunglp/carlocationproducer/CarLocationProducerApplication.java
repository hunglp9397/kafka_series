package com.hunglp.carlocationproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CarLocationProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarLocationProducerApplication.class, args);
	}

}
