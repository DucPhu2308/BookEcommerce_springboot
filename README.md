# LEETTRUYEN
## Giới thiệu
Phần Backend của dự án Web Đọc Truyện được xây dựng bằng Spring Boot, là một framework Java phổ biến cho việc phát triển ứng dụng web. Phần này chịu trách nhiệm xử lý các yêu cầu từ phía Frontend, quản lý dữ liệu và cung cấp các API cho phần giao diện người dùng.
## Cài đặt và chạy
1. Clone repository về máy tính của bạn:
```bash
git clone https://github.com/DucPhu2308/BookEcommerce_springboot.git
```
2. Mở terminal và di chuyển vào thư mục đã được clone:
```bash
cd BookEcommerce_springboot
```
3. Cài đặt Maven dependencies:
```
mvn install
```
4. Thiết lập cấu hình cơ sở dữ liệu:Chỉnh sửa file application.properties trong thư mục src/main/resources để cấu hình kết nối đến cơ sở dữ liệu PostgreSQL:
```bash
spring.datasource.url=jdbc:postgresql://dpg-co243ia0si5c73cqaq00-a.singapore-postgres.render.com:5432/leettruyen
spring.datasource.username=ducphu
spring.datasource.password=qwNlcnNsCrZyqoN8HuHerKuF85EkVnUY
```
5. Chạy ứng dụng Spring Boot:
```
mvn spring-boot:run
```
## Công nghệ sử dụng
- Frontend: ReactJS được sử dụng cho phần giao diện người dùng.
- Backend: Spring Boot để xây dựng API cho ứng dụng.
  - Spring Data JPA: Cung cấp một cách thuận tiện để truy cập và lưu trữ dữ liệu trong cơ sở dữ liệu.
  - Spring Security: Sử dụng để quản lý xác thực và ủy quyền người dùng.
  - JWT (JSON Web Tokens): Được sử dụng để xác thực và ủy quyền người dùng.
  - Bcrypt: Sử dụng để mã hóa mật khẩu người dùng.
- Database: PostgreSQL được sử dụng để lưu trữ thông tin về các tác phẩm văn học và người dùng.
## Tính năng
1. **Quản lý truyện**: Tạo, đọc, mua, cập nhật và xóa thông tin về truyện.
2. **Quản lý người dùng**: Đăng ký, đăng nhập và quản lý thông tin người dùng.
3. **Xác thực và ủy quyền**: Sử dụng JWT để xác thực người dùng và kiểm tra quyền truy cập vào các tài nguyên.
4. **Tìm kiếm và lọc truyện**: Cung cấp api cho phép tìm kiếm và lọc truyện theo nhiều tiêu chí.
5. **Quản lý theo dõi**: Người dùng có thể theo dõi truyện hoặc tác giả yêu thích của mình.
6. **Quản lý doanh thu**: Người dùng có thể theo dõi được doanh thu bán truyện của mình.
7. **Quản lý giao dịch**: Người dùng có thể xem lịch sử các giao dịch nạp tiền và mua chương truyện trong tài khoản của mình.
