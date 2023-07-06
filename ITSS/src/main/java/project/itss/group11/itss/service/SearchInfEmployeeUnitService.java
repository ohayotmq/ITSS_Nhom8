package project.itss.group11.itss.service;

import javafx.collections.ObservableList;
import project.itss.group11.itss.model.Employee;

public interface SearchInfEmployeeUnitService {
    ObservableList<Employee> employeeObservableList(int unitID);
}
