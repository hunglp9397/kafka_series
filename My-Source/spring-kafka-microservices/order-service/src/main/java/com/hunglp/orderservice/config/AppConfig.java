package com.hunglp.orderservice.config;


import com.hunglp.orderservice.controller.OrderController;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AppConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);
    @Bean
    public NewTopic orders() {

        LOG.info("Created topic : orders");
        return TopicBuilder.name("orders")
                .partitions(3)
                .build();


    }

    @Bean
    public NewTopic paymentTopic() {
        LOG.info("Created topic : payment-orders");
        return TopicBuilder.name("payment-orders")
                .partitions(3)
                .compact()
                .build();
    }

    @Bean
    public NewTopic stockTopic() {
        LOG.info("Created topic : stock-orders");
        return TopicBuilder.name("stock-orders")
                .partitions(3)
                .compact()
                .build();
    }

}
