package project.itss.group11.itss.service.Impl;

import javafx.collections.ObservableList;
import project.itss.group11.itss.model.Employee;
import project.itss.group11.itss.repository.EmployeeInforRepository;
import project.itss.group11.itss.repository.Impl.EmployeeInforRepositoryImpl;
import project.itss.group11.itss.service.SearchInfEmployeeUnitService;

import java.util.List;

public class SearchInfEmployeeUnitServiceImpl implements SearchInfEmployeeUnitService {
    private static final EmployeeInforRepository employeeInforRepo = new EmployeeInforRepositoryImpl();

    @Override
    public ObservableList<Employee> employeeObservableList(int unitID) {

        return employeeInforRepo.getEmployeeUnitInfor(unitID);

    }
}
