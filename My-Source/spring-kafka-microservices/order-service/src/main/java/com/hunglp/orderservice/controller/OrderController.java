package com.hunglp.orderservice.controller;

import com.hunglp.orderservice.model.Order;

import com.hunglp.orderservice.service.OrderManageService;
import com.hunglp.orderservice.service.OrderProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private AtomicLong id = new AtomicLong();

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }


    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) throws ExecutionException, InterruptedException {
        orderProducer.sendOrderEvent(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
