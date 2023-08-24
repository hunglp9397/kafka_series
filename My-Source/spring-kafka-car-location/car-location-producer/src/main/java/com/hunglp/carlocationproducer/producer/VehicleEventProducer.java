package com.hunglp.carlocationproducer.producer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hunglp.carlocationproducer.model.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.apache.kafka.common.header.Header;

import java.util.List;

@Component
@Slf4j
public class VehicleEventProducer {

    private final String topic  = "vehicle-position-events";

    private final KafkaTemplate<String, Vehicle> kafkaTemplate;

    private final ObjectMapper objectMapper;

    public VehicleEventProducer(KafkaTemplate<String, Vehicle> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    public ListenableFuture<SendResult<String,Vehicle>> sendVehicleEvents(Vehicle vehicle) {

        String key = vehicle.getVin();

        ProducerRecord<String,Vehicle> producerRecord = buildProducerRecord(key, vehicle, topic);

        ListenableFuture<SendResult<String,Vehicle>> listenableFuture =  kafkaTemplate.send(producerRecord);
        listenableFuture.addCallback(new VehicleEventListenableFutureCallback(key,vehicle));

        return listenableFuture;
    }

    private ProducerRecord<String, Vehicle> buildProducerRecord(String key, Vehicle value, String topic) {

        List<Header> recordHeaders = List.of(new RecordHeader("event-source", "Rest Call".getBytes()));

        return new ProducerRecord<>(topic, null, key, value, recordHeaders);
    }



}
