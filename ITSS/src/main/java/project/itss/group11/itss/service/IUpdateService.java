package project.itss.group11.itss.service;

import project.itss.group11.itss.model.Form;

public interface IUpdateService {
    public int acceptChangeInfor(Form form);
    public int rejectChangeInfor(int formID);
}
