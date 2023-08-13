package com.hunglp.springkafkabasic;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "hunglptopic", groupId = "groupId")
    void listener(String data) {
        System.out.println("Listen received data: " + data);
    }
}
