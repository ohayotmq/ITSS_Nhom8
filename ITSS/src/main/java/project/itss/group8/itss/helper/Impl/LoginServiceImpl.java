package project.itss.group8.itss.helper.Impl;

import project.itss.group8.itss.model.Employee;
import project.itss.group8.itss.subsystem.EmployeeInforRepository;
import project.itss.group8.itss.subsystem.Impl.EmployeeInforRepositoryImpl;
import project.itss.group8.itss.subsystem.Impl.UserRepositoryImpl;
import project.itss.group8.itss.subsystem.UserRepository;
import project.itss.group8.itss.helper.LoginService;

public class LoginServiceImpl implements LoginService {
     private static final UserRepository userRepository = new UserRepositoryImpl();
     private static final EmployeeInforRepository employeeRepository = new EmployeeInforRepositoryImpl();

    @Override
    public boolean checkLogin(String user, String pass) {
        return pass.equals(userRepository.getPass(user));
    }

    @Override
    public Employee getUserInfor(int ID) {
        return employeeRepository.getInforUser(ID);
    }

}
