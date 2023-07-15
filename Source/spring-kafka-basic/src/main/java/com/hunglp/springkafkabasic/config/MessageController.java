package com.hunglp.springkafkabasic.config;


import com.hunglp.springkafkabasic.MessageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/")
public class MessageController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @PostMapping("/messages")
    public void publish(@RequestBody MessageReq messageReq){
        kafkaTemplate.send("hunglptopic", messageReq.getContent());
        System.out.println("Sent message");
    }
}
