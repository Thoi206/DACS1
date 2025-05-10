package dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimeConverter {
    // Chuyển LocalDateTime thành Timestamp để lưu vào SQL Server
    public static Timestamp toTimestamp(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return Timestamp.valueOf(dateTime);
    }

    // Chuyển Timestamp từ SQL Server thành LocalDateTime để dùng trong Java
    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}