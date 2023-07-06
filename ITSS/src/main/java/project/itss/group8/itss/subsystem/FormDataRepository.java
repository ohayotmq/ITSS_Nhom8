package project.itss.group8.itss.subsystem;

import javafx.collections.ObservableList;
import project.itss.group8.itss.model.FormDatabase;

public interface FormDataRepository {
    ObservableList<FormDatabase> getFormData();
    int updateForm(int formID, int status);
}
