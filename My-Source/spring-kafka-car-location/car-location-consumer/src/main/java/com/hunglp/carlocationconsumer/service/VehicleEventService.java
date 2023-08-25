package com.hunglp.carlocationconsumer.service;

import com.hunglp.carlocationconsumer.model.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.Instant;

@Service
@Slf4j
public class VehicleEventService {



    @Autowired
    KafkaTemplate<String, Vehicle> kafkaTemplate;

    public void processVehicleEvent(ConsumerRecord<String, Vehicle> consumerRecord) {
        Vehicle vehicle = consumerRecord.value();
        log.info("vehicleEvent : {} ", vehicle);
    }

    public void handleRecovery(ConsumerRecord<String,Vehicle> record){
        log.error("handleRecovery for {}", record);

        String key = record.key();
        Vehicle message = record.value();
        //String message = record.value().replace(":0",":-1");

        ListenableFuture<SendResult<String,Vehicle>> listenableFuture = kafkaTemplate.sendDefault(key, message);
        listenableFuture.addCallback(new VehicleEventListenableFutureCallback(key,message));


    }



}
