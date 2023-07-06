package project.itss.group8.itss.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ViewAllOfficersController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backBtn;

    @FXML
    private DatePicker date;

    @FXML
    private Button filter;

    @FXML
    private Text name;

    @FXML
    private TableColumn<?, ?> officerID;

    @FXML
    private TableColumn<?, ?> officerName;

    @FXML
    private TableColumn<?, ?> officerUnit;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<?> tableView;

    @FXML
    private Button toWorkerView;

    @FXML
    private TableColumn<?, ?> totalFaultHours;

    @FXML
    private TableColumn<?, ?> totalWorkDays;

    @FXML
    private Label unit;

    @FXML
    private ComboBox<?> unitList;

    @FXML
    private TableColumn<?, ?> viewDetailBtn;

    @FXML
    private TableColumn<?, ?> workMonth;

    @FXML
    void backToPreviousPage(ActionEvent event) {

    }

    @FXML
    void filterTimekeepingByMonth(ActionEvent event) {

    }

}
