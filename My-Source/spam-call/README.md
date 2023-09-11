# DEMO SPAM CALL 

------------------------------------------------------------------------------------------------------------------------
## Phần 1: Requirements
### 1. Tổng quan project 
- Phát hiện thuê bao spam theo 2 điều kiện sau:
   + Called có nhãn là spam ussd
   + Hoặc
   + (caller gọi trên 3 cuộc / phút)


### 2. Kiến trúc:
- Module **send-ussd** làm 2 việc sau:
   + Tạo cuộc gọi để làm dữ liệu test, bắn message sang Module Realtime
   + Xử lí response Ussd khảo sát dựa vào các luât ở trên để phát hiện thuê bao spam

- Module **realtime-processing** làm 2 việc sau:
   + Consume thông tin cuộc gọi, Thực hiện gửi Ussd khảo sát tới số called,
   + Giả lập dl phản hồi khảo sát, Rồi bắn lại kết quả khảo sát sang module "send-ussd"

- Mô hình như sau:

------------------------------------------------------------------------------------------------------------------------
## Phần 2 : Implement



