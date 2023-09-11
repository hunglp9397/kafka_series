package com.hunglp.springkafkabatchprocessing;

import com.hunglp.springkafkabatchprocessing.consumer.Receiver;

import com.hunglp.springkafkabatchprocessing.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class SpringKafkaBatchProcessingApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaBatchProcessingApplication.class, args);
	}

	@Autowired
	private Sender sender;

	@Override
	public void run(String... strings)  {
		int numberOfMessages = Receiver.COUNT;
		for (int i = 0; i < numberOfMessages; i++) {
			sender.send("simple-batch", "message " + i);
		}
	}
}
