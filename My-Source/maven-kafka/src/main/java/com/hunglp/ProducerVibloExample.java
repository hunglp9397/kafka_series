package com.hunglp;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
public class ProducerVibloExample {
    private static final Logger log = LoggerFactory.getLogger(ProducerVibloExample.class);


    public static void main(String[] args) {


        String bootstrapServers = "127.0.0.1:9092,127.0.0.1:9093";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());



        final var producer = new KafkaProducer<String, String>(properties);
        for (int i = 0; i < 100; i++) {
            final var message = new ProducerRecord<>(
                    "new-kafka-topic",
                    "key-" + i,
                    "message: " + i
            );
            producer.send(message);
        }
        producer.flush();
        producer.close();


    }

}