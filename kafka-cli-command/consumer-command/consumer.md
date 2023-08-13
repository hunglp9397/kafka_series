# CÁC LỆNH KAFKA CONSUMERS


### 1. Consume tất cả message từ đầu đến giờ
Note : thêm command --from-beginning

````bash
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --from-beginning
````

### 2. Consume message hiện tại:
```bash
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic
```


### 3. Consume message, hiển thị cả key và value

````bash
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.key=true --property print.value=true --from-beginning
````
-> Kết quả:
````bash
CreateTime:1691934968022        null    Hello
CreateTime:1691934973252        null    Hung
CreateTime:1691935320864        null    Hello
CreateTime:1691935320874        null    1997
CreateTime:1691934972013        null    Iam
CreateTime:1691936749416        key1    value1
CreateTime:1691936752487        key2    value2 
````