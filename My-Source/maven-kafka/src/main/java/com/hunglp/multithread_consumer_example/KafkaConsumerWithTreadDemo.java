package com.hunglp.multithread_consumer_example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class KafkaConsumerWithTreadDemo {

    public static void main(String[] args) {
        new KafkaConsumerWithTreadDemo().run();
    }

    public KafkaConsumerWithTreadDemo() {
    }

    private void run() {
        Logger logger = LoggerFactory.getLogger(KafkaConsumerWithTreadDemo.class);

        String bootstrapServer = "127.0.0.1:9092";
        String groupId = "my-third-gfg-group";
        String topic = "gfg_topic";

        // CountDownLatch for dealing with multiple threads
        CountDownLatch latch = new CountDownLatch(1);

        // Create the Consumer Runnable
        logger.info("Creating the consumer thread");
        ConsumerThread myConsumerThread = new ConsumerThread(topic, bootstrapServer, groupId, latch);

        // Start the Thread
        Thread myThread = new Thread(myConsumerThread);
        myThread.start();

        // Add a Shutdown Hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Caught shutdown hook");
            myConsumerThread.shutDown();
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("Application has exited");
        }

        ));

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Application got interrupted", e);
        } finally {
            logger.info("Application is Closing");
        }
    }

    public class ConsumerThread implements Runnable {

        private final CountDownLatch latch;
        KafkaConsumer<String, String> consumer;
        private final Logger logger = LoggerFactory.getLogger(ConsumerThread.class);

        public ConsumerThread(String topic, String bootstrapServer, String groupId, CountDownLatch latch) {

            this.latch = latch;

            // Create Consumer Properties
            Properties properties = new Properties();
            properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
            properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

            // Create Consumer
            consumer = new KafkaConsumer<>(properties);

            // Subscribe Consumer to Our Topics
            consumer.subscribe(List.of(topic));
        }

        @Override
        public void run() {
            try {
                // Poll the data
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                    for (ConsumerRecord<String, String> record : records) {
                        logger.info("Key: " + record.key() +
                                " Value: " + record.value() +
                                " Partition: " + record.partition() +
                                " Offset: " + record.offset()
                        );
                    }
                }
            } catch (WakeupException e) {
                logger.info("Received shutdown signal");
            } finally {
                consumer.close();
                // Tell our main code
                // We are done
                // with the consumer
                latch.countDown();
            }
        }

        public void shutDown() {
            // The wakeup() method is used
            // to interrupt consumer.poll()
            // It will throw WakeUpException
            consumer.wakeup();
        }
    }

}