import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;
import project.itss.group11.itss.model.Form;
import project.itss.group11.itss.service.*;
import project.itss.group11.itss.service.Impl.FormOverviewServiceImpl;
import java.time.LocalDateTime;

public class SuaCC {

    IFormOverviewService formOverviewService = new FormOverviewServiceImpl();

    @Test
    public void test1() {
        ObservableList<Form> dataList = formOverviewService.getFormData();
        for (Form form : dataList) {
            System.out.println(form.getIdform());
        }
    }
}
