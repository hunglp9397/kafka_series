package com.hunglp.multithread_producer_example;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;

public class MultithreadedKafkaProducer {

    public static void main(String[] args) {
        int numThreads = 3;             // Number of producer threads
        String topicName = "my-topic"; // Kafka topic to produce messages to

        for (int i = 0; i < numThreads; i++) {
            ProducerThread producerThread = new ProducerThread(topicName);
            Thread thread = new Thread(producerThread);
            thread.start();
        }
    }

    static class ProducerThread implements Runnable {
        private final String topicName;
        private final Producer<String, String> producer;

        public ProducerThread(String topicName) {
            this.topicName = topicName;

            // Configure Kafka producer properties
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Kafka broker(s) address
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

            this.producer = new KafkaProducer<>(props);
        }

        @Override
        public void run() {
            try {
                // Produce messages to Kafka topic
                for (int i = 0; i < 10; i++) {
                    String message = "Message " + i;
                    producer.send(new ProducerRecord<>(topicName, message), new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            if (exception != null) {
                                System.err.println("Error sending message: " + exception.getMessage());
                            } else {
                                System.out.println("Produced: " + message);
                            }
                        }
                    });
                    Thread.sleep(1000); // Sleep for 1 second between producing messages
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                producer.close(); // Close the producer when done
            }
        }
    }
}