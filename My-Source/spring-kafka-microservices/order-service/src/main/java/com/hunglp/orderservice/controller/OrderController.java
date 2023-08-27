package com.hunglp.orderservice.controller;

import com.hunglp.orderservice.model.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private AtomicLong id = new AtomicLong();
    private KafkaTemplate<Long, Order> kafkaTemplate;

    @PostMapping
    public Order create(@RequestBody Order order) {
        order.setId(id.incrementAndGet());
        kafkaTemplate.send("order-topic", order.getId(), order);
        LOG.info("Sent: {}", order);
        return order;
    }
}
