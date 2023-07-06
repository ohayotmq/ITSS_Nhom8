package project.itss.group8.itss.helper;

import javafx.collections.ObservableList;
import project.itss.group8.itss.model.Employee;

public interface SearchInfEmployeeUnitService {
    ObservableList<Employee> employeeObservableList(int unitID);
}
