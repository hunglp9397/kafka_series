
## Các ví dụ Kafka
1Start Kafka như trong mục "install"
2. Cấu hình 1 topic như sau:
    -![img_1.png](images_readme/img_1.png)
3. Ktra topic ở cmd Windows bằng lệnh: `kafka-topics --bootstrap-server localhost:9092 --list`
    - Kết quả
   ![img_2.png](images_readme/img_2.png)
4. Kiểm tra nội dung topic ở cmd Ubuntu bằng lệnh : ``kafka-console-consumer.sh --topic hunglptopic --from-beginning --bootstrap-server localhost:9092``
    - Kết quả:
   ![img_3.png](images_readme/img_3.png)
5. Event message:
    - ![img_4.png](images_readme/img_4.png)
6. Consume message:
    - ![img_5.png](images_readme/img_5.png)


## Triển khai trên Docker
1. Build file jar
2. Tao docker file:
```dockerfile
FROM openjdk:11
COPY target/spring-kafka-basic-0.0.1-SNAPSHOT.jar /app/spring-kafka-basic-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "spring-kafka-basic-0.0.1-SNAPSHOT.jar"]
```
3. Tạo repo tren docker hub, Rồi push images lên docker hub
```shell
docker push 123497/spring-kafka-basic:latest
```

3. Tạo dokercompose

4. Dockerize
```shell
   docker compose up -d
```

5. Kết qua:
```shell
PS D:\Workspace\Learning\kafka_series\My-Source\spring-kafka-basic> docker ps
CONTAINER ID   IMAGE                              COMMAND                  CREATED          STATUS          PORTS                                        NAMES
f16e95aa70a8   123497/spring-kafka-basic:latest   "java -jar spring-ka…"   36 minutes ago   Up 36 minutes   0.0.0.0:8083->8083/tcp                       spring-kafka-basic
140c91bedf36   confluentinc/cp-kafka:latest       "/etc/confluent/dock…"   36 minutes ago   Up 36 minutes   0.0.0.0:9092->9092/tcp                       spring-kafka-basic-kafka-1
6b207d1d27aa   confluentinc/cp-zookeeper:latest   "/etc/confluent/dock…"   36 minutes ago   Up 36 minutes   2888/tcp, 0.0.0.0:2181->2181/tcp, 3888/tcp   spring-kafka-basic-zookeeper-1

```