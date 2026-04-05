package com.example.kafka.model;

public class Order {
    private String orderId;
    private String productName;
    private double price;

    public Order() {
    }

    public Order(String orderId, String productName, double price) {
        this.orderId = orderId;
        this.productName = productName;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{orderId='" + orderId + "', productName='" + productName + "', price=" + price + '}';
    }
}
