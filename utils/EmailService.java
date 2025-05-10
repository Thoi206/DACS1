package utils;

import model.Schedule;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    private static final String FROM_EMAIL = "thoideptrai2k6@gmail.com"; // Thay bằng email thật
    private static final String APP_PASSWORD = "jtba xfsy kral vdon\n"; // Thay bằng App Password Gmail

    public static void sendEmail(Schedule schedule) {
        // Cấu hình SMTP cho Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo phiên làm việc có xác thực
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        try {
            // Tạo email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(schedule.getEmailNotification())
            );
            message.setSubject("Thông báo sự kiện: " + schedule.getTitle());

            // Nội dung email
            String body = String.format(
                    "Thông tin sự kiện:\n" +
                            "Tiêu đề: %s\n" +
                            "Địa điểm: %s\n" +
                            "Thời gian bắt đầu: %s\n" +
                            "Thời gian kết thúc: %s\n" +
                            "Mô tả: %s\n" +
                            "Chu kỳ lặp: %s\n" +
                            "Số lần lặp: %d\n" +
                            "Thời gian báo trước: %d phút",
                    schedule.getTitle(),
                    schedule.getLocation(),
                    schedule.getStartTime(),
                    schedule.getEndTime(),
                    schedule.getDescription(),
                    schedule.getRepeatCycle(),
                    schedule.getNumberOfOccurrences(),
                    schedule.getRemindBefore().toMinutes()
            );

            message.setText(body);
            Transport.send(message);

            System.out.println("Email đã được gửi đến " + schedule.getEmailNotification());

        } catch (MessagingException e) {
            System.err.println("Lỗi khi gửi email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
