package project.itss.group8.itss.helper.Impl;

import project.itss.group8.itss.model.Form;
import project.itss.group8.itss.subsystem.FormDataRepository;
import project.itss.group8.itss.subsystem.Impl.FormDataRepositoryImpl;
import project.itss.group8.itss.subsystem.Impl.LogInforRepositoryImpl;
import project.itss.group8.itss.subsystem.LogInforRepository;
import project.itss.group8.itss.helper.IUpdateService;

public class UpdateServiceImpl implements IUpdateService {

    LogInforRepository repository = new LogInforRepositoryImpl();
    FormDataRepository formDataRepository = new FormDataRepositoryImpl();
    @Override
    public int acceptChangeInfor(Form form) {
        int updateLogInfor = repository.updateInfor(form);
        int updateForm = formDataRepository.updateForm(form.getIdform(),1);
        return updateForm+updateLogInfor;
    }

    @Override
    public int rejectChangeInfor(int formID) {
        return formDataRepository.updateForm(formID,-1);
    }
}
