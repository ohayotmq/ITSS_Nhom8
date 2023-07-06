package project.itss.group11.itss.model;

import java.time.LocalDateTime;

public class TimekeepingOverview {
    private int day;
    private String start;
    private String end;
    private String comeLate;
    private String returnEarly;

    public int getDay() {
        return day;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getComeLate() {
        return comeLate;
    }

    public String getReturnEarly() {
        return returnEarly;
    }

    public TimekeepingOverview(int day, String start, String end, String comeLate, String returnEarly) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.comeLate = comeLate;
        this.returnEarly = returnEarly;
    }
    public TimekeepingOverview() {

    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setComeLate(String comeLate) {
        this.comeLate = comeLate;
    }

    public void setReturnEarly(String returnEarly) {
        this.returnEarly = returnEarly;
    }
}
