
# KAFKA STREAM : WORD COUNT EXAMPLE


### 1.Tạo topic


Tạo topic sentences để produce word

```bash
kafka-topics.sh --bootstrap-server localhost:9092 --topic sentences --create --partitions 3 --replication-factor 1
```


### 2 Chạy file SpringKafkaWordCountApplication để stream data kafka
Kết quả:

### 3. Produce message
Produce message tới topic: sentences

```bash
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic sentences
>hello kakfa
>hello Vietnam
>hello Khue
>Hello Tran Duong Khue
```

### 4. Consumer message

- Consume message topic "word-count"
- Lưu ý : ko cần tạo thủ công bằng tay topic word-count do code đã làm rồi

```bash
kafka-console-consumer.sh --topic word-count --bootstrap-server localhost:9092 --from-beginning --property print.key=true --property key.separator=" : " --key-deserializer "org.apache.kafka.common.serialization.StringDeserializer" --value-deserializer "org.apache.kafka.common.serialization.LongDeserializer"
hello : 1
kakfa : 1
hello : 2
vietnam : 1
hello : 3
khue : 1
hello : 4
tran : 1
khue : 2
duong : 1
```






Done!



