# KAFKA: 
1. Giới thi chung
- Là một nền tảng stream dữ liệu phân tán
  

2. Một số usecase dùng kafka : 
  - Dùng làm mesage broker 
  - Acitvity Tracking
  - Đo lường
  - Tạo log
  - Stream processing
3. Các thành phần của Kafka:
 - Topics
   + Là một stream data đặc biệt, tưong tự như table trong datatable
   + Có thể có nhiều topcis nếu muốn
   + Các topic được xác định bằng tên của topic
   + Là một loại của message format
   + Thứ tự mesage được gọi là datastream
   + Không thể truy vấn topics, Sử dụng Kafka Producer để send data và kafka consumer để đọc data
 
 - Partitions: 
   + Topics được chia thành nhiều partitions
   + Trên mỗi dải partitions có nhiều id (gọi là offsets)
   
    => Một khi data được ghi vào partitions, nó không thể bị thay đổi => Không thể xóa data trong KAFKA
    
    Ví dụ: 1 cty vận chuyển có nhiều xe tải, mỗi xe tải phải thông báo vị itris GPS tới Kafka
          Mỗi xe tải sẽ send message tới Kafka mỗi 20giây, Message chứa : TruckID, TruckPosition( latitude, longitude)
    
    

 


