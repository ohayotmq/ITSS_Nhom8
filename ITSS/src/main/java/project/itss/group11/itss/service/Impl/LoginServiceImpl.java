package project.itss.group11.itss.service.Impl;

import project.itss.group11.itss.model.Employee;
import project.itss.group11.itss.repository.EmployeeInforRepository;
import project.itss.group11.itss.repository.Impl.EmployeeInforRepositoryImpl;
import project.itss.group11.itss.repository.Impl.UserRepositoryImpl;
import project.itss.group11.itss.repository.UserRepository;
import project.itss.group11.itss.service.LoginService;

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
