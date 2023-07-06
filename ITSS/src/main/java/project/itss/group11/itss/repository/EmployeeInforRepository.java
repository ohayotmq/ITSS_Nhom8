package project.itss.group11.itss.repository;

import javafx.collections.ObservableList;
import project.itss.group11.itss.model.Employee;

import java.util.List;

public interface EmployeeInforRepository {
    ObservableList<Employee> getEmployeeUnitInfor(int unitID);
    Employee getInforUser(int id);

}
