package com.hunglp.carlocationconsumer.consumer;

import com.hunglp.carlocationconsumer.model.Vehicle;
import com.hunglp.carlocationconsumer.service.VehicleEventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VehicleEventsConsumer {


    @Autowired
    VehicleEventService vehicleEventService;

    @KafkaListener(topics = {"vehicle-position-events"})
    public void onMessage(ConsumerRecord<String, Vehicle> consumerRecord) {
        log.info("ConsumerRecord : {} ", consumerRecord );
        vehicleEventService.processVehicleEvent(consumerRecord);

    }
}