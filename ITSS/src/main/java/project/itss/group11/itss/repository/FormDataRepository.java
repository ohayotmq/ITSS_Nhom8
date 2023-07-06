package project.itss.group11.itss.repository;

import javafx.collections.ObservableList;
import project.itss.group11.itss.model.FormDatabase;

import java.util.List;

public interface FormDataRepository {
    ObservableList<FormDatabase> getFormData();
    int updateForm(int formID, int status);
}
