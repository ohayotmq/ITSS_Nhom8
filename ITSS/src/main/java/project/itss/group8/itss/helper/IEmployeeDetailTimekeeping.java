package project.itss.group8.itss.helper;

import javafx.collections.ObservableList;
import project.itss.group8.itss.model.TimekeepingDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IEmployeeDetailTimekeeping {
    public String getReturnEarlyTime(LocalDateTime returningTime, LocalDateTime endTime);
    public String getComeLateTime(LocalDateTime comingTime,LocalDateTime startTime);

    public ObservableList<TimekeepingDetail> getDetailTimekeepingByDay(LocalDate time, LocalDateTime start,
                                                                       LocalDateTime end);

}
