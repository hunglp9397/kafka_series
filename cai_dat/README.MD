Link : 
https://www.conduktor.io/kafka/how-to-install-apache-kafka-on-windows/



**B1 : Start Zookeeper** `zookeeper-server-start.sh ~/kafka_2.13-3.0.0/config/zookeeper.properties`

**B2 : Start Kafka** : `kafka-server-start.sh ~/kafka_2.13-3.0.0/config/server.properties`



FIx Lỗi : 
- Trong Ubuntu APP
    cd tới thư mục kafka, chạy lệnh sau : `nano config/server.properties`
    ![img.png](img.png)

- Sửa Listener thành như sau:
![img_2.png](img_2.png)
- Sau đó vẫn trong thư mục kafka 3.0.0, chạy lệnh : `kafka-server-start.sh config/server.properties`