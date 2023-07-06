package project.itss.group8.itss.helper;

import project.itss.group8.itss.model.Employee;

public interface LoginService {
    boolean checkLogin(String user, String pass);

    Employee getUserInfor(int ID);
}
