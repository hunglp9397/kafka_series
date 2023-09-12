package com.hunglp.config.producer;

import com.hunglp.config.model.UssdSurvey;
import com.hunglp.config.util.JsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class UssdSurveyProducer {

    private static final Logger log = LogManager.getLogger(UssdSurveyProducer.class);

    private static KafkaProducer<Integer, UssdSurvey> producer;

    static {
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "realtime");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        producer = new KafkaProducer<>(props);
    }


    public static void produceUssdSurvey(UssdSurvey ussdSurvey){

        ProducerRecord<Integer, UssdSurvey> record= new ProducerRecord<>("ussd-survey", ussdSurvey);
        try {
            RecordMetadata recordMetadata = producer.send(record).get();

            String message = String.format("sent message to topic:%s partition:%s  offset:%s",
                    recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());

            log.info(message);

        } catch (InterruptedException e) {
            log.error("Error white produce message to kafka! Exception: " + e.getMessage());
        } catch (ExecutionException e) {
            log.error("Error white produce message to kafka! Exception: " + e.getMessage());
        }
    }
    public static void produceListUssdSurvey(List<UssdSurvey> ussdSurveyList){

//        Properties props = new Properties();
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, "realtime");
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        KafkaProducer<Integer, UssdSurvey> producer = new KafkaProducer<>(props);



        for(int i = 0; i < ussdSurveyList.size(); i++) {

            ProducerRecord<Integer, UssdSurvey> record= new ProducerRecord<>("ussd-survey", i, ussdSurveyList.get(i));
            try {
                RecordMetadata recordMetadata = producer.send(record).get();

                String message = String.format("sent message to topic:%s partition:%s  offset:%s",
                        recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());

                log.info(message);

            } catch (InterruptedException e) {
                log.error("Error white produce message to kafka! Exception: " + e.getMessage());
            } catch (ExecutionException e) {
                log.error("Error white produce message to kafka! Exception: " + e.getMessage());
            }

        }

    }
}
