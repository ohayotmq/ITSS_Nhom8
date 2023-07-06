package project.itss.group8.itss.helper;

import project.itss.group8.itss.model.Form;

public interface IUpdateService {
    public int acceptChangeInfor(Form form);
    public int rejectChangeInfor(int formID);
}
