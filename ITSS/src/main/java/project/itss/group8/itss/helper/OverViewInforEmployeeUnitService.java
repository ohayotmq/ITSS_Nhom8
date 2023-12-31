package project.itss.group8.itss.helper;

import javafx.collections.ObservableList;
import project.itss.group8.itss.model.TimekeepingOverview;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface OverViewInforEmployeeUnitService {
    public int getDay(int month,int Year);
    public String getStartTime(LocalDateTime time);

    public ObservableList<TimekeepingOverview> getTimekeepingByMonth(LocalDate time, LocalDateTime start,
                                                                     LocalDateTime end, int employeeId);
}
