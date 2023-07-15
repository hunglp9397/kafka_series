package com.hunglp.springkafkabasic.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic testTopic(){
        return TopicBuilder.name("hunglptopic").build();
    }
}
