# CÁC LỆNH KAFKA CƠ BẢN

### 1. Tạo topics:

- Tạo topic với 3 partitions và 1 replication:
    ```bash
   kafka-topics.sh --bootstrap-server localhost:9092 --topic first-topic --create --partitions 3 --replication-factor 1
  ```
### 2. Show list topics:
```bash
kafka-topics.sh --bootstrap-server localhost:9092 --list
```

### 3. Hiển thị chi tiết topics 

```bash
kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic first-topic
```
- _Kết quả:_
```bash
  Topic: first_topic	TopicId: fN9mf1UDSmiCdKIDFyMEIQ	PartitionCount: 3	ReplicationFactor: 1	Configs: cleanup.policy=delete
  Topic: first_topic	Partition: 0	Leader: 1	Replicas: 1	Isr: 1
  Topic: first_topic	Partition: 1	Leader: 1	Replicas: 1	Isr: 1
  Topic: first_topic	Partition: 2	Leader: 1	Replicas: 1	Isr: 1
```
- Giải thích : 
  + Leader: 1  nghĩa là đối với partition 0,  broker với ID 1 là  leader.

  + Replicas: 1 nghĩa là đối với  partition 0, broker với  ID 1 là replica.

  + Isr: 1 nghĩa là đối với partition 0, broker với là ID 1 là một  in-sync replica(ISR).
