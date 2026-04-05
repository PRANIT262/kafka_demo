package com.example.kafka.service;

import com.example.kafka.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    private static final String TOPIC = "orders";

    public void sendOrder(Order order) {
        kafkaTemplate.send(TOPIC, String.valueOf(order.getOrderId()), order);
        System.out.println("JSON Order sent successfully: " + order.toString());
    }
}
