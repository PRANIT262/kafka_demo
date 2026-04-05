package com.example.kafka.service;

import com.example.kafka.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    // CONSUMER 1: The Inventory System
    @KafkaListener(topics = "orders", groupId = "inventory-group")
    public void consumeForInventory(Order order) {
        System.out.println("[INVENTORY SYSTEM] Deducting stock for Product: " + order.getProductName());
    }

    // CONSUMER 2: The Notification System
    @KafkaListener(topics = "orders", groupId = "notification-group")
    public void consumeForNotification(Order order) {
        System.out.println("[NOTIFICATION SYSTEM] Emailing Customer Confirmation for Order ID: " + order.getOrderId());
    }
}
