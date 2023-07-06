package project.itss.group11.itss.service;

import javafx.collections.ObservableList;
import project.itss.group11.itss.model.TimekeepingDetail;
import project.itss.group11.itss.model.TimekeepingOverview;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IEmployeeDetailTimekeeping {
    public String getReturnEarlyTime(LocalDateTime returningTime, LocalDateTime endTime);
    public String getComeLateTime(LocalDateTime comingTime,LocalDateTime startTime);

    public ObservableList<TimekeepingDetail> getDetailTimekeepingByDay(LocalDate time, LocalDateTime start,
                                                                       LocalDateTime end);

}
