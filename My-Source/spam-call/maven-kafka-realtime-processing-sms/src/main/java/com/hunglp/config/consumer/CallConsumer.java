package com.hunglp.config.consumer;


import com.hunglp.config.model.CallingInfo;
import com.hunglp.config.model.UssdSurvey;
import com.hunglp.config.producer.UssdSurveyProducer;
import com.hunglp.config.util.JsonDeserializer;
import com.hunglp.config.util.SurveyBuilder;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class CallConsumer {

    private static final Logger log = LogManager.getLogger(CallConsumer.class);

    public static void consumeCallInfo(){



        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.CLIENT_ID_CONFIG, "realtime");
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        consumerProps.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, CallingInfo.class);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "realtime-group");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, CallingInfo> consumer = new KafkaConsumer<>(consumerProps);

        consumer.subscribe(Arrays.asList("call-info"));


        while (true) {
            ConsumerRecords<String, CallingInfo> records = consumer.poll(Duration.ofMillis(200));

            AtomicInteger i = new AtomicInteger();
            records.forEach(record ->{
                log.info(record.value());
                CallingInfo callingInfo = record.value();
                UssdSurvey ussdSurvey = SurveyBuilder.generateSurvey(i.get(), callingInfo);
                i.getAndIncrement();
                UssdSurveyProducer.produceUssdSurvey(ussdSurvey);
            });

        }
    }




}
