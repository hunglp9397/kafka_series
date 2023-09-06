package com.hunglp.multithread_producer_example;

/*

1. MultiThread trong Kafka producer là một kĩ thuật để tăng thông lượng cho producer bằng cách cho phép gửi message đến nhiều topics khác nhau

2. Lợi ích của multiThread Producer
- Xử lí song song: Cho phép gửi tin đến nhiều topics khác nhau song song -> Làm tăng thông lượng của producer
- Cân bằng tải : Cho phép gửi xử lí trên nhiều luồng -> Giúp cân bằng tải trên cụm Kafka, tránh overload trên một broker hoặc một partition duy nhất -> Đảm bảo message được gửi đồng đều
- Xử lí bất đồng bộ: Kafka producer có thể tip tục gửi tin nhắn trong khi vẫn đang chờ ack từ kafka
- Cải thiện khả năng chịu lỗi: Với nhiều luồng thì ngày cả khi một luồng nào đó lỗi thì một luồng khác có thể tiếp tục xử lí
- Tối đa viêc sử dụng tài nguyên
- Có thể sử dụng để áp dụng việc lưu vào bộ nhớ đệm rồi gửi batch message -> Giảm việc gửi từng tin riêng lẻ -> Cải thiện được hiệu suất
- Dễ dàng scale khi cần mở rộng quy mô, xử lí một lượng tin nhắn cao hơn























*/