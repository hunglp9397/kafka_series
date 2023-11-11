
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
 