package com.hunglp.springkafkasendussd.service;


import com.hunglp.springkafkasendussd.model.CallingInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CallingProducer {

    private static final Logger log = LoggerFactory.getLogger(CallingProducer.class);

    private final KafkaTemplate<String, CallingInfo> kafkaTemplate;


    public CallingProducer(KafkaTemplate<String, CallingInfo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void call(CallingInfo callingInfo) throws ExecutionException, InterruptedException {
        SendResult<String, CallingInfo> sendResult = kafkaTemplate.send("call-info", callingInfo).get();
        log.info("Send Calling-Info : {} via Kafka", callingInfo);
        log.info(sendResult.toString());
    }
}
