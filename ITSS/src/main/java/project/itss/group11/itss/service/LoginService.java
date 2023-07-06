package project.itss.group11.itss.service;

import project.itss.group11.itss.model.Employee;

public interface LoginService {
    boolean checkLogin(String user, String pass);

    Employee getUserInfor(int ID);
}
