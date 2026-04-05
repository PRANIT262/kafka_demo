# 📦 Kafka Demo Project

A hands-on **Spring Boot + Apache Kafka** demo application that simulates an **Order Processing System**. It demonstrates core Kafka concepts like producers, consumers, consumer groups, topics, and partitions using a real-world order workflow.

---

## 🏗️ Tech Stack

| Technology       | Version |
|------------------|---------|
| Java             | 17      |
| Spring Boot      | 3.2.3   |
| Spring Kafka     | (managed by Spring Boot) |
| Apache Kafka     | (external broker) |
| Build Tool       | Maven   |

---

## 📁 Project Structure

```
kafka-demo/
├── pom.xml
├── Readme.md
└── src/main/
    ├── java/com/example/kafka/
    │   ├── KafkaDemoApplication.java         # Spring Boot entry point
    │   ├── config/
    │   │   └── KafkaTopicConfig.java          # Programmatic topic creation
    │   ├── controller/
    │   │   └── OrderController.java           # REST API endpoints
    │   ├── model/
    │   │   └── Order.java                     # Order POJO (orderId, productName, price)
    │   └── service/
    │       ├── KafkaProducerService.java       # Sends orders to Kafka topic
    │       └── KafkaConsumerService.java       # Listens & processes orders
    └── resources/
        └── application.properties             # Kafka broker & serialization config
```

---

## 🧠 Core Concepts Demonstrated

### 1. Topics & Partitions
- The `orders` topic is created programmatically with **3 partitions** and **1 replica**.
- Partitions allow parallel processing of messages.

### 2. Producer
- `KafkaProducerService` uses `KafkaTemplate<String, Order>` to serialize and send `Order` objects as JSON to the `orders` topic.
- The `orderId` is used as the **message key**, which determines partition assignment.

### 3. Consumer Groups
Two independent consumer groups listen to the **same** topic, each receiving **all** messages:

| Consumer Group        | Purpose                                  |
|-----------------------|------------------------------------------|
| `inventory-group`     | Deducts stock for the ordered product    |
| `notification-group`  | Sends email confirmation for the order   |

### 4. JSON Serialization / Deserialization
- **Producer** uses `JsonSerializer` to convert `Order` objects → JSON.
- **Consumer** uses `JsonDeserializer` to convert JSON → `Order` objects.

---

## 🚀 Getting Started

### Prerequisites
- **Java 17+** installed
- **Apache Kafka** running locally on port `9092`
- **Maven** installed

### 1. Start Kafka (using Kafka's bundled scripts)

```bash
# Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# Start Kafka Broker
bin/kafka-server-start.sh config/server.properties
```

> **Windows users:** Use the `.bat` scripts inside `bin/windows/` instead.

### 2. Build & Run the Application

```bash
# Build
mvn clean install

# Run
mvn spring-boot:run
```

The application starts on **http://localhost:8080**.

---

## 📡 API Endpoints

### Send a Single Order

```http
POST /orders
Content-Type: application/json
```

**Request Body:**
```json
{
  "orderId": "ORD-001",
  "productName": "Laptop",
  "price": 999.99
}
```

**Response:**
```
Complex Order JSON sent to Kafka!
```

---

### Send Bulk Orders (5 orders)

```http
POST /orders/bulk
```

**Response:**
```
5 Bulk Orders fired into Kafka!
```

This fires 5 auto-generated orders (`BULK-1` through `BULK-5`) to simulate high-volume traffic.

---

## 📋 Console Output Example

When you send an order, both consumer groups process it independently:

```
JSON Order sent successfully: Order{orderId='ORD-001', productName='Laptop', price=999.99}
[INVENTORY SYSTEM] Deducting stock for Product: Laptop
[NOTIFICATION SYSTEM] Emailing Customer Confirmation for Order ID: ORD-001
```

---

## ⚙️ Configuration (`application.properties`)

```properties
# Kafka Broker
spring.kafka.bootstrap-servers=localhost:9092

# Consumer
spring.kafka.consumer.group-id=my-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=*

# Producer
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
```

---

## 🧪 Testing with cURL

```bash
# Single order
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{"orderId":"ORD-001","productName":"Laptop","price":999.99}'

# Bulk orders
curl -X POST http://localhost:8080/orders/bulk
```

---

## 📚 Key Takeaways

- **Topics** are named channels where messages are published.
- **Partitions** enable parallelism — messages with the same key always go to the same partition.
- **Consumer Groups** allow multiple independent systems to process the same stream of events.
- **Spring Kafka** makes it easy to integrate Kafka with just annotations (`@KafkaListener`) and auto-configuration.

---

## 📄 License

This project is for **learning and demonstration purposes**.