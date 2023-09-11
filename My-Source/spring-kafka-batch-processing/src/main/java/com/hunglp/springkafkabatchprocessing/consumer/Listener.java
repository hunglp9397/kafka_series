package com.hunglp.springkafkabatchprocessing.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @KafkaListener(id = "batch-listener", topics = "${kafka.topic.batch}")
    void listener(String data) {
        System.out.println("Listen received data: " + data);
    }
}
