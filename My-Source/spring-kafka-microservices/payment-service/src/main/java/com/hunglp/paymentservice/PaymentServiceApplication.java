package com.hunglp.paymentservice;

import com.hunglp.paymentservice.domain.Order;
import com.hunglp.paymentservice.service.OrderManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableKafka
public class PaymentServiceApplication {


	private static final Logger LOG = LoggerFactory.getLogger(PaymentServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}


}
