package project.itss.group8.itss.subsystem;

import javafx.collections.ObservableList;
import project.itss.group8.itss.model.Employee;

public interface EmployeeInforRepository {
    ObservableList<Employee> getEmployeeUnitInfor(int unitID);
    Employee getInforUser(int id);

}
