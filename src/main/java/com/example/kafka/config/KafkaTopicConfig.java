package com.example.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic ordersTopic() {
        // This programmatically establishes our topic inside the Broker if it doesn't exist
        return TopicBuilder.name("orders")
                .partitions(3)  // Creates 3 parallel lanes for processing 
                .replicas(1)    // Amount of backup copies across different brokers
                .build();
    }
}
