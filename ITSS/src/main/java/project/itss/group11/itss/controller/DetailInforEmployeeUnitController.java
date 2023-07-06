package project.itss.group11.itss.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import project.itss.group11.itss.model.TimekeepingDetail;
import project.itss.group11.itss.service.DetailInforEmployeeUnitService;
import project.itss.group11.itss.service.IEmployeeDetailTimekeeping;
import project.itss.group11.itss.service.Impl.DetailInforEmployeeUnitServiceImpl;
import project.itss.group11.itss.service.Impl.EmployeeDetailTimekeeping;
import project.itss.group11.itss.service.Impl.LoginServiceImpl;
import project.itss.group11.itss.service.LoginService;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DetailInforEmployeeUnitController extends WorkspaceController{

    private static final DetailInforEmployeeUnitService service = new DetailInforEmployeeUnitServiceImpl();
    private static final LoginService loginService = new LoginServiceImpl();
    ObservableList<TimekeepingDetail> timekeepingDetails = FXCollections.observableArrayList();
    LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 8, 00, 0);
    LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 17, 30, 0);

    @FXML
    private Text username;

    @FXML
    private Text id_Employee;

    @FXML
    private TableColumn<TimekeepingDetail, String> comeLate;

    @FXML
    private Text date;

    @FXML
    private TableColumn<TimekeepingDetail, Integer> machine;

    @FXML
    private TableColumn<TimekeepingDetail, String> returnEarly;

    @FXML
    private TableColumn<TimekeepingDetail, String> time;

    @FXML
    private TableColumn<TimekeepingDetail, String> type;

    @FXML
    private TableView<TimekeepingDetail> tableview;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button backBtn;
    public void initialize(){
        AnchorPane.setTopAnchor(tableview, 100.0);
        AnchorPane.setLeftAnchor(tableview, 10.0);
        AnchorPane.setRightAnchor(tableview, 10.0);
        AnchorPane.setBottomAnchor(tableview, 10.0);
        AnchorPane.setRightAnchor(backBtn, 10.0);


        username.setText(loginService.getUserInfor(SearchInfEmployeeUnitController.employeeID).getName());
        id_Employee.setText(String.valueOf(SearchInfEmployeeUnitController.employeeID));
        date.setText(LocalDate.now().toString());
        //Constant.pool = ConnectionPool.getInstance("etc/database.config");
        date.setText(OverViewInforEmployeeUnitController.localDate.toString());
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        machine.setCellValueFactory(new PropertyValueFactory<>("machine"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        returnEarly.setCellValueFactory(new PropertyValueFactory<>("returnEarly"));
        comeLate.setCellValueFactory(new PropertyValueFactory<>("comeLate"));

        timekeepingDetails = service.getDetailTimekeepingByDay(OverViewInforEmployeeUnitController.localDate,startTime,endTime,SearchInfEmployeeUnitController.employeeID);
        tableview.setItems(timekeepingDetails);


    }
    @FXML
    void backToPreviousPage(ActionEvent event) {
        try {
            changeWorkspace("/project/itss/group11/itss/view/manager/OverViewEmployeeUnit.fxml");
        }catch (Exception e) {
            logger.error("Error in BacktoPreviousPage");
        }
    }



}
