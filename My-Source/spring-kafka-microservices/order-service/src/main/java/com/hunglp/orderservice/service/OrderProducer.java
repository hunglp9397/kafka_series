package com.hunglp.orderservice.service;

import com.hunglp.orderservice.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class OrderProducer {

    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);

    private final KafkaTemplate<String, Order> kafkaTemplate;


    public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(Order order) throws ExecutionException, InterruptedException {
        SendResult<String, Order> sendResult = kafkaTemplate.send("orders", order).get();
        log.info("Create order {} event sent via Kafka", order);
        log.info(sendResult.toString());
    }
}