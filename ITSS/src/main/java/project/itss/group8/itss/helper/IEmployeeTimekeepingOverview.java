package project.itss.group8.itss.helper;

import javafx.collections.ObservableList;
import project.itss.group8.itss.model.TimekeepingOverview;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IEmployeeTimekeepingOverview {
    public int getDay(int month,int Year);
    public String getStartTime(LocalDateTime time);
    public String getEndTime(LocalDateTime time);
    public String getReturnEarlyTime(LocalDateTime returningTime,LocalDateTime endTime);
    public String getComeLateTime(LocalDateTime commingTime,LocalDateTime startTime);

    public ObservableList<TimekeepingOverview> getTimekeepingByMonth(LocalDate time, LocalDateTime start,
                                                                     LocalDateTime end);
}
