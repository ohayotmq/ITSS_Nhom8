package project.itss.group8.itss.helper.Impl;

import javafx.collections.ObservableList;
import project.itss.group8.itss.model.Employee;
import project.itss.group8.itss.subsystem.EmployeeInforRepository;
import project.itss.group8.itss.subsystem.Impl.EmployeeInforRepositoryImpl;
import project.itss.group8.itss.helper.SearchInfEmployeeUnitService;

public class SearchInfEmployeeUnitServiceImpl implements SearchInfEmployeeUnitService {
    private static final EmployeeInforRepository employeeInforRepo = new EmployeeInforRepositoryImpl();

    @Override
    public ObservableList<Employee> employeeObservableList(int unitID) {

        return employeeInforRepo.getEmployeeUnitInfor(unitID);

    }
}
