package com.hunglp.paymentservice.service;


import com.hunglp.paymentservice.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "orders", containerFactory="OrderContainerFactory")
    public void orderListener(@Payload Order order, Acknowledgment ack) {
        log.info("Consumer : received order {} ", order);
        ack.acknowledge();
    }
}
