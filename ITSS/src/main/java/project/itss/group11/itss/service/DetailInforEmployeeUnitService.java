package project.itss.group11.itss.service;

import javafx.collections.ObservableList;
import project.itss.group11.itss.model.TimekeepingDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DetailInforEmployeeUnitService {

    public ObservableList<TimekeepingDetail> getDetailTimekeepingByDay(LocalDate time, LocalDateTime start,
                                                                       LocalDateTime end, int employeeId);

}
