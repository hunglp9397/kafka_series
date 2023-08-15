# KAFKA BASIC PRODUCE - CONSUME MESSAGE

### 1. Tạo topic:
- Tạo topic demo_java với partition = 3, replica-factor = 1 
```bash
kafka-topics.sh --bootstrap-server localhost:9092 --topic demo_java --create --partitions 3 --replication-factor 1
```

### 2. Tạo consumer:
```bash
`kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic demo_java`
```
### 3. Run file Producer.java  =>  consume được message:
```bash
hunglp@HungLP:~$ kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic demo_java

hello world
```

