# Kafka Consumer Group Management CLI


## 1. Cách để reset 1 consumer groups

#### B1: Điều kiện tiên quyết: Dừng tất cả các consumer trong consumer groups

```bash
hunglp@HungLP:~$ kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group my-first-application

Consumer group 'my-first-application' has no active members.

GROUP                TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID     HOST            CLIENT-ID
my-first-application first_topic     0          1               1               0               -               -               -
my-first-application first_topic     1          1               1               0               -               -               -
my-first-application first_topic     2          1               1               0               -               -               -
```

#### B2: Reset offsets về vị trí gần nhất để đọc lại topic lần nữa
```bash
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --to-earliest --execute --topic first_topic

GROUP                          TOPIC                          PARTITION  NEW-OFFSET
my-first-application           first_topic                    0          0
my-first-application           first_topic                    1          0
my-first-application           first_topic                    2          0
```
==> Tất cả offset mới cho tất cả các partitions là 0
==> Do vậy khi restart 1 consumer nào đó trong group  -> Consumer đó sẽ consume message từ đầu của partition


```bash
hunglp@HungLP:~$ kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-first-application
third_message
first_message
second_message
```


## 2. Show tất cả consumer trong 1 consumer groups:
```bash
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group my-first-application

GROUP                TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID                                                          HOST            CLIENT-ID
my-first-application first_topic     0          3               3               0               consumer-my-first-application-1-0237b0a1-911d-45f1-891f-8fd7630a7593 /172.19.0.1     consumer-my-first-application-1
my-first-application first_topic     1          5               5               0               consumer-my-first-application-1-70ddc756-8dbc-45e4-b5b6-1e5a75db9e62 /172.19.0.1     consumer-my-first-application-1
my-first-application first_topic     2          6               6               0               consumer-my-first-application-1-a8ce2af3-97b3-4445-bba3-f4a5f6c4464d /172.19.0.1     consumer-my-first-application-1
```

- Trong đó : 
  + _CONSUMER ID_: Đại diện cho ID duy nhất của một consumer tới Kafka broker
  + _CLIENT ID_ : Đại diện cho consumer_id để xác định ở client  
  + _CURRENT-OFFSET_ : Là commit offset mới nhất trong group
  + _LOG-END-OFFSET_: Đại diện cho message mới nhất có thể dùng dể consume trong topic-partition
  + _LAG_ =  _LOG-END-OFFSET_ - _CURRENT-OFFSET_
  + _HOST_ : Là IP của consumer phía client


## 3. Xóa một consumer groups:
```bash
$ kafka-consumer-groups.sh --bootstrap-server localhost:9092 --delete --group my-first-application
Deletion of requested consumer groups ('my-first-application') was successful.
```

## 4 Tương tự, nếu muốn xóa một ofset cho một topic nhất định thì dùng lệnh sau:

```bash
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --delete-offsets --group my-first-application --topic first_topic
```
(Hữu ích khi consumer group đang consumer message từ nhiều topic)

```bash
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --delete-offsets --group my-first-application --topic first_topic
TOPIC                          PARTITION       STATUS
first_topic                    0               Successful
first_topic                    1               Successful
first_topic                    2               Successful
````