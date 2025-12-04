# Hướng Dẫn Lấy Gmail App Password

## Tại sao cần App Password?
Gmail không cho phép dùng mật khẩu thường để gửi email từ ứng dụng. Bạn cần tạo "App Password" - mật khẩu đặc biệt cho ứng dụng.

## Các Bước Lấy App Password

### Bước 1: Bật 2-Step Verification
1. Vào: https://myaccount.google.com/security
2. Tìm mục **"Signing in to Google"**
3. Click **"2-Step Verification"**
4. Làm theo hướng dẫn để bật (cần số điện thoại)

### Bước 2: Tạo App Password
1. Sau khi bật 2-Step Verification, quay lại: https://myaccount.google.com/security
2. Tìm mục **"Signing in to Google"**
3. Click **"App passwords"** (hoặc "Mật khẩu ứng dụng")
4. Có thể cần nhập mật khẩu Gmail lại
5. Chọn:
   - **Select app:** Mail
   - **Select device:** Other (Custom name)
   - Nhập tên: "Java Email App"
6. Click **"Generate"**
7. Copy mật khẩu 16 ký tự (dạng: `abcd efgh ijkl mnop`)

### Bước 3: Cập Nhật Code

Mở file: `src/main/java/murach/util/MailUtilGmail.java`

Tìm dòng 16-17:
```java
final String username = "phucquan2k4@gmail.com"; // TODO: Thay bằng email của bạn
final String password = "your-app-password-here"; // TODO: Thay bằng App Password
```

Thay bằng:
```java
final String username = "your-email@gmail.com"; // Email Gmail của bạn
final String password = "abcdefghijklmnop"; // App Password (bỏ dấu cách)
```

**Lưu ý:** App Password không có dấu cách, viết liền: `abcdefghijklmnop`

### Bước 4: Cập Nhật Email Gửi

Mở file: `src/main/java/murach/email/EmailListServlet.java`

Tìm dòng 59:
```java
String from = "phucquan2k4@gmail.com"; // TODO: Thay bằng email của bạn
```

Thay bằng email Gmail của bạn:
```java
String from = "your-email@gmail.com";
```

### Bước 5: Build và Test
```cmd
mvn clean package
```

Restart Tomcat và test!

## Kiểm Tra Kết Quả

1. Vào: http://localhost:8080/
2. Nhập tên và email của bạn
3. Click "Join Now"
4. Kiểm tra email inbox (có thể trong Spam)
5. Bạn sẽ nhận được email chào mừng!

## Troubleshooting

### Lỗi: "Username and Password not accepted"
- Kiểm tra lại App Password (không có dấu cách)
- Đảm bảo 2-Step Verification đã bật
- Thử tạo App Password mới

### Lỗi: "Connection timed out"
- Kiểm tra kết nối internet
- Firewall có thể chặn port 587
- Thử tắt antivirus tạm thời

### Email không nhận được
- Kiểm tra thư mục Spam
- Đợi vài phút (có thể bị delay)
- Xem Tomcat logs để kiểm tra lỗi

## Bảo Mật

**QUAN TRỌNG:**
- Không commit App Password lên Git!
- Không chia sẻ App Password với ai
- Có thể thu hồi App Password bất cứ lúc nào tại: https://myaccount.google.com/apppasswords

## Alternative: Dùng Email Service Khác

Nếu không muốn dùng Gmail, có thể dùng:
- **SendGrid** (free 100 emails/day)
- **Mailgun** (free 5000 emails/month)
- **Mailtrap** (chỉ để test, không gửi thật)

Nhưng Gmail là đơn giản nhất cho bài tập!
