package project.itss.group8.itss.helper.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.itss.group8.itss.model.Form;
import project.itss.group8.itss.model.FormDatabase;
import project.itss.group8.itss.subsystem.FormDataRepository;
import project.itss.group8.itss.subsystem.Impl.FormDataRepositoryImpl;
import project.itss.group8.itss.helper.IFormOverviewService;

import java.util.List;

public class FormOverviewServiceImpl implements IFormOverviewService {
    FormDataRepository formDataRepository = new FormDataRepositoryImpl();

    @Override
    public ObservableList<Form> getFormData() {
        System.out.println("Get formdata");
        ObservableList<Form> forms = FXCollections.observableArrayList();
        List<FormDatabase> formDatabases = formDataRepository.getFormData();
        for (FormDatabase formDatabase : formDatabases) {
            Form form = new Form(formDatabase.getIdform(), formDatabase.getIdnhanvien(),
                    formDatabase.getOldtime(),
                    formDatabase.getNewtime(),
                    formDatabase.getOldDevice(),
                    formDatabase.getNewDevice(),
                    formDatabase.getIdlog());
            forms.add(form);
            System.out.println(form);
        }
        System.out.println("Getted formdata");
        return forms;
    }
}
