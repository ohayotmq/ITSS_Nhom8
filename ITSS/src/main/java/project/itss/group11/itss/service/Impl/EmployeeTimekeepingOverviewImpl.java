package project.itss.group11.itss.service.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.itss.group11.itss.Until.Constant;
import project.itss.group11.itss.model.LogInfor;
import project.itss.group11.itss.model.TimekeepingOverview;
import project.itss.group11.itss.repository.Impl.LogInforRepositoryImpl;
import project.itss.group11.itss.repository.LogInforRepository;
import project.itss.group11.itss.service.IEmployeeTimekeepingOverview;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTimekeepingOverviewImpl implements IEmployeeTimekeepingOverview {

    LogInforRepository logInforRepository = new LogInforRepositoryImpl();
    @Override
    public int getDay(int Month,int year) {
        List<Integer> month31day = new ArrayList<>(List.of(1, 3, 5, 7, 8, 10,12));
        List<Integer> month30day = new ArrayList<>(List.of(4,6,9,11));
        if(month31day.contains(Month))
            return 31;
        if(month30day.contains(Month))
            return 30;
        if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
            return 29;
        else return 28;
    }

    @Override
    public String getStartTime(LocalDateTime time) {
        return time.getHour()+":"+time.getMinute();
    }

    @Override
    public String getEndTime(LocalDateTime time) {
        return time.getHour()+":"+time.getMinute();
    }

    @Override
    public String getReturnEarlyTime(LocalDateTime returningTime, LocalDateTime endTime) {
       boolean isReturningEarly = returningTime.getHour()*3600 + returningTime.getMinute()*60 + returningTime.getSecond()
               < endTime.getHour()*3600 + endTime.getMinute()*60 + endTime.getSecond();
       if(isReturningEarly)
           return "Yes";
       else
           return "No";
    }

    @Override
    public String getComeLateTime(LocalDateTime comingTime, LocalDateTime startTime) {
        boolean isComeLate = comingTime.getHour()*3600 + comingTime.getMinute()*60 + comingTime.getSecond()
                > startTime.getHour()*3600 + startTime.getMinute()*60 + startTime.getSecond();
        if(isComeLate)
            return "Yes";
        else
            return "No";
    }

    @Override
    public ObservableList<TimekeepingOverview> getTimekeepingByMonth(LocalDate time,LocalDateTime start, LocalDateTime end) {
        ObservableList<TimekeepingOverview> timekeepingOverviews = FXCollections.observableArrayList();
        List<LogInfor> logInfors = logInforRepository.getLogInforByMonth(time.getMonth().getValue(),time.getYear(), Constant.employee.getID());
        LocalDateTime current_time = LocalDateTime.now();
        LocalDateTime date1,date2;
        TimekeepingOverview t1 = new TimekeepingOverview();

        int current_day = current_time.getDayOfMonth();
        for(int i = 0;i<logInfors.size();i++)
        {
            LogInfor log = logInfors.get(i);

            date1 = log.getTimeStamp();
            t1 = new TimekeepingOverview(date1.getDayOfMonth()
                    , getStartTime(date1),null,getComeLateTime(date1,start),
                null);
            // Nếu có đử cả sáng và chiều
            if(i+1 < logInfors.size()){
                date2 = logInfors.get(i+1).getTimeStamp();
                t1.setEnd(getEndTime(date2));
                t1.setReturnEarly(getReturnEarlyTime(date2,end));
            }
            timekeepingOverviews.add(t1);
            i+=1;
        }
        return timekeepingOverviews;
    }

}
