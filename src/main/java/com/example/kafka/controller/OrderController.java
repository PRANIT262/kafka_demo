package com.example.kafka.controller;

import com.example.kafka.model.Order;
import com.example.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private KafkaProducerService producerService;

    // Single order producer
    @PostMapping
    public String createOrder(@RequestBody Order order) {
        producerService.sendOrder(order);
        return "Complex Order JSON sent to Kafka!";
    }

    // Bulk order producer (Simulating multi-producer/high-volume traffic)
    @PostMapping("/bulk")
    public String createBulkOrders() {
        for(int i = 1; i <= 5; i++) {
            Order order = new Order("BULK-" + i, "Widget-" + i, 50.0 * i);
            producerService.sendOrder(order);
        }
        return "5 Bulk Orders fired into Kafka!";
    }
}
