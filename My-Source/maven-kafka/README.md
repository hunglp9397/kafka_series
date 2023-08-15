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

### Console log ở Consumer.java khi thực hiện produce message khác nhau ở file Producer.java

```bash
[main] INFO com.hunglp.Consumer - Key: null, Value: hello world
[main] INFO com.hunglp.Consumer - Partition: 2, Offset:0
[main] INFO com.hunglp.Consumer - Key: null, Value: tim em bao la
[main] INFO com.hunglp.Consumer - Partition: 2, Offset:1
[main] INFO com.hunglp.Consumer - Key: null, Value: Mac Ke doi cho nguoi ta
[main] INFO com.hunglp.Consumer - Partition: 2, Offset:2
```

