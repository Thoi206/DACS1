package dao;
import model.Schedule;
import dao.TimeConverter;
import javax.print.attribute.standard.SheetCollate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
//****
import java.time.Duration;
public class ScheduleDao {
    public List<Schedule> getAllSchedule(int userId) throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM Schedule1  WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt("id"));
                schedule.setUserId(rs.getInt("user_id")); // Lấy user_id
                schedule.setTitle(rs.getString("title"));
                schedule.setLocation(rs.getString("location"));
                // Sử dụng TimeConverter để chuyển đổi thời gian
                schedule.setStartTime(TimeConverter.toLocalDateTime(rs.getTimestamp("startTime")));
                schedule.setEndTime(TimeConverter.toLocalDateTime(rs.getTimestamp("endTime")));
                schedule.setEmailNotification(rs.getString("emailNotification"));
                schedule.setRemindBefore(Duration.ofMinutes(rs.getInt("remindBefore")));
                schedule.setNumberOfOccurrences(rs.getInt("numberOfOccurrences"));
                schedule.setInterval(Duration.ofMinutes(rs.getInt("intervalMinutes")));
                schedule.setRepeatCycle(rs.getString("repeatCycle"));
                schedule.setDescription(rs.getString("Description"));

                schedules.add(schedule);
            }
        }
        return schedules;
    }

    public void addSchedule(Schedule schedule) throws SQLException{
        String query = "INSERT INTO Schedule1 (title, location, startTime, endTime, emailNotification, " +
                "remindBefore, numberOfOccurrences, intervalMinutes, repeatCycle, Description, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt= conn.prepareStatement(query)) {
            stmt.setString(1, schedule.getTitle());
            stmt.setString(2, schedule.getLocation());
            // Dùng TimeConverter để chuyển đổi thời gian
            stmt.setTimestamp(3, TimeConverter.toTimestamp(schedule.getStartTime()));
            stmt.setTimestamp(4, TimeConverter.toTimestamp(schedule.getEndTime()));
            stmt.setString(5, schedule.getEmailNotification());
            stmt.setInt(6, (int) schedule.getRemindBefore().toMinutes());
            stmt.setInt(7, schedule.getNumberOfOccurrences());
            stmt.setInt(8, (int) schedule.getInterval().toMinutes());
            stmt.setString(9, schedule.getRepeatCycle());
            stmt.setString(10, schedule.getDescription());
            stmt.setInt(11, schedule.getUserId());
            //***
            stmt.executeUpdate();
        }
    }
    public void updateSchedule(Schedule schedule) throws SQLException{
        String query = "UPDATE Schedule1 SET title = ?, location = ?, startTime = ?, endTime = ?, " +
                "emailNotification = ?, remindBefore = ?, numberOfOccurrences = ?, " +
                "intervalMinutes = ?, repeatCycle = ?, Description = ?, user_id = ? WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, schedule.getTitle());
            stmt.setString(2, schedule.getLocation());
            // Dùng TimeConverter để chuyển đổi thời gian
            stmt.setTimestamp(3, TimeConverter.toTimestamp(schedule.getStartTime()));
            stmt.setTimestamp(4, TimeConverter.toTimestamp(schedule.getEndTime()));
            stmt.setString(5, schedule.getEmailNotification());
            stmt.setInt(6, (int) schedule.getRemindBefore().toMinutes());
            stmt.setInt(7, schedule.getNumberOfOccurrences());
            stmt.setInt(8, (int) schedule.getInterval().toMinutes());
            stmt.setString(9, schedule.getRepeatCycle());
            stmt.setString(10, schedule.getDescription());
            stmt.setInt(11, schedule.getUserId()); // Cập nhật user_id
            stmt.setInt(12, schedule.getId());

            stmt.executeUpdate();
        }
    }
    public void deleteSchedule(int id, int userId) throws SQLException{
        String query = "DELETE FROM Schedule1  WHERE id = ? AND user_id = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1,id);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
        }
    }

    public List<Schedule> searchScheduleByTitle(String title,int userId) throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM Schedule1  WHERE title LIKE ? AND user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + title + "%");
            pstmt.setInt(2, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Schedule schedule = extractScheduleFromResultSet(rs);
                    schedules.add(schedule);
                }
            }
        }
        return schedules;
    }

    public List<Schedule> searchScheduleByDateRange(Timestamp fromDate, Timestamp toDate,int userId) throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM Schedule1  WHERE startTime >= ? AND endTime <= ? AND user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setTimestamp(1, fromDate);
            pstmt.setTimestamp(2, toDate);
            pstmt.setInt(3, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Schedule schedule = extractScheduleFromResultSet(rs);
                    schedules.add(schedule);
                }
            }
        }
        return schedules;
    }



    //ham dung cua 2 ham tim kiem
    private Schedule extractScheduleFromResultSet(ResultSet rs) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getInt("id"));
        schedule.setUserId(rs.getInt("user_id")); // Dòng này bị thiếu
        schedule.setTitle(rs.getString("title"));
        schedule.setLocation(rs.getString("location"));
        schedule.setStartTime(TimeConverter.toLocalDateTime(rs.getTimestamp("startTime")));
        schedule.setEndTime(TimeConverter.toLocalDateTime(rs.getTimestamp("endTime")));
        schedule.setEmailNotification(rs.getString("emailNotification"));
        schedule.setRemindBefore(Duration.ofMinutes(rs.getInt("remindBefore")));
        schedule.setNumberOfOccurrences(rs.getInt("numberOfOccurrences"));
        schedule.setInterval(Duration.ofMinutes(rs.getInt("intervalMinutes")));
        schedule.setRepeatCycle(rs.getString("repeatCycle"));
        schedule.setDescription(rs.getString("Description"));
        return schedule;
    }

    // Phương thức hỗ trợ chuyển đổi LocalDateTime sang Timestamp
   /* private Timestamp toTimestamp(LocalDateTime dateTime) {
        return dateTime != null ? Timestamp.valueOf(dateTime) : null;
    }

    public boolean authenticate(String username, String password) throws SQLException {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }*/


}