package project.itss.group11.itss.service.Impl;

import project.itss.group11.itss.model.Form;
import project.itss.group11.itss.repository.FormDataRepository;
import project.itss.group11.itss.repository.Impl.FormDataRepositoryImpl;
import project.itss.group11.itss.repository.Impl.LogInforRepositoryImpl;
import project.itss.group11.itss.repository.LogInforRepository;
import project.itss.group11.itss.service.IUpdateService;

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
