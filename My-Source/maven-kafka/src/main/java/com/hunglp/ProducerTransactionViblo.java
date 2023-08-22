package com.hunglp;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerTransactionViblo {
    public static void main(String[] args) {

        String bootstrapServers = "127.0.0.1:9092,127.0.0.1:9093";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "demo-transaction-id");

        final var producer = new KafkaProducer<String, String>(properties);
        producer.initTransactions();

        try {
            producer.beginTransaction();
            producer.send(new ProducerRecord<>("transaction-topic-1", "Message to topic 1"));
            producer.send(new ProducerRecord<>("transaction-topic-2", "Message to topic 2"));
            producer.commitTransaction();
        } catch (Exception ex) {
            producer.abortTransaction();
            producer.close();
            throw new RuntimeException(ex);
        }
        producer.close();

    }
}
