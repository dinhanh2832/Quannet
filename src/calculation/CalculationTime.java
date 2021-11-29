package calculation;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class CalculationTime {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String timeStart;
    private String timeClose;

    public CalculationTime() {
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public String getTime() {
        long year = LocalDateTime.now().getYear();
        long month = LocalDateTime.now().getMonthValue();
        long day = LocalDateTime.now().getDayOfMonth();
        long hour = LocalDateTime.now().getHour();
        long minute = LocalDateTime.now().getMinute();
        String year1 = String.valueOf(year);
        String month1 = String.valueOf(month);
        String day1 = String.valueOf(day);
        String hour1 = String.valueOf(hour);
        String minute1 = String.valueOf(minute);
        return year1 + "-" + month1 + "-" + day1 + " " + hour1 + ":" + minute1;
    }

    public String getTimeStart() {
        timeStart = getTime();
        return timeStart;
    }

    public String getTimeClose() {
        timeClose = getTime();
        return timeClose;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeClose(String timeClose) {
        this.timeClose = timeClose;
    }

    public long getUserTime(String timeStart, String timeClose) {
        LocalDateTime dateTime1 = LocalDateTime.parse(timeStart, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(timeClose, formatter);
        return java.time.Duration.between(dateTime1, dateTime2).toMinutes();
    }

    public static void main(String[] args) {
    }
}
