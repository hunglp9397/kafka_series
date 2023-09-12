package com.hunglp.springkafkasendussd.service;

import com.hunglp.springkafkasendussd.model.UssdSurvey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class UssdSurveyConsumer {

    private static final Logger log = LoggerFactory.getLogger(UssdSurveyConsumer.class);


    @KafkaListener(topics = "ussd-survey", containerFactory="ussdSurveyContainerFactory")
    public void orderListener(@Payload UssdSurvey ussdSurvey, Acknowledgment ack) {
        log.info("Consumer : received ussdSurvey {} ", ussdSurvey);
        ack.acknowledge();

    }
}