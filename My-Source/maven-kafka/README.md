# KAFKA BASIC PRODUCE - CONSUME MESSAGE


## Phần 1. Produce SingleThread
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

## Phần 2: Các loại ack

### 1. Đặt vấn đề:

![img.png](imgs/img.png)
-  Trường hợp Replication Leader hoạt động tốt:
  + Sau khi nhận message từ producer, Kafka append vào partition log để đảm bảo ko bị mất message
  +  Mặc định, producer chờ ack từ kafka để biết chắc chắn message đã được append ở Replication Leader hay chưa
  + Nếu chưa được append ở replication Leader, thì sẽ thực hiển retry cho đến khi thành công

- Trường hợp Replication Leader chết  => Một trong các ISR phải lên thay

### 2. Các trường hợp acks
#### 2.1. acks=1 (Giá trị  mặc định)

- Kafka sẽ ack lại cho producer ngay sau khi Replication Leader append log thành công
- Ko chờ sync log sang các ISR (Flow 1 -> 2 -> 3)
- Suy ra trường hợp ack lại Producer xong (3 xong), mà Replication Leader bị chết thì ISR khác lên thay nhưng chưa sync được messsage -> lost msage
- ![img.png](imgs/1.png)

### 2.2 acks = all
- Do vậy muốn chắc chắn ko lost message, thì việc append log  cần được xảy ra ở tất cả các ISR trướng khi Replication Leader ack lại cho Producer
- Mô tả hình như sau:
 + ![img.png](imgs/2.png)
- VIệc ko loss message đáp ứng cho các bài toán yêu cầu cao tính durability


### 2.3 acks = 0
- Trường hợp bài toán yêu cầu cao về throughput mà ko quá quan trọng tính durability
- Lúc này Producer send message mà ko quan tâm việc có được append log thành công vào Replication Leader hay ISR hay ko


## Phần 3: Producer transaction
- Ý nghĩa của producer transaction : Cần gửi các message tới đến nhiều topic khác nhau và muốn tất cả gửi thành công, Nếu có bất kì lỗi nào thì sẽ ko có message nào gửi thành công
- 