package model;
import java.time.LocalDateTime;
//****
import java.time.Duration;

public class Schedule {
    private int id;
    private int userId;
    private String title;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String emailNotification;
    private Duration remindBefore; // Báo trước (ví dụ: 5 phút trước)
    private int numberOfOccurrences; // Số lần lặp
    private Duration interval; // Khoảng thời gian lặp (ví dụ: mỗi 1 ngày)
    private String repeatCycle; // Chu kỳ lặp (Không lặp, Hàng ngày, Hàng tuần,...)
    private String Description;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(String emailNotification) {
        this.emailNotification = emailNotification;
    }

    public Duration getRemindBefore() {
        return remindBefore;
    }

    public void setRemindBefore(Duration remindBefore) {
        this.remindBefore = remindBefore;
    }

    public int getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public void setNumberOfOccurrences(int numberOfOccurrences) {
        this.numberOfOccurrences = numberOfOccurrences;
    }

    public Duration getInterval() {
        return interval;
    }

    public void setInterval(Duration interval) {
        this.interval = interval;
    }

    public String getRepeatCycle() {
        return repeatCycle;
    }

    public void setRepeatCycle(String repeatCycle) {
        this.repeatCycle = repeatCycle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return title + " (" +
                (startTime != null ? startTime.toString().replace("T", " ") : "Chưa đặt") + " - " +
                (endTime != null ? endTime.toString().replace("T", " ") : "Chưa đặt") + ")";
    }
}