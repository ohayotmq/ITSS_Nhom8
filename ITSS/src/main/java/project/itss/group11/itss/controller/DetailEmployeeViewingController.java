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
import project.itss.group11.itss.Until.ConnectionPool;
import project.itss.group11.itss.Until.Constant;
import project.itss.group11.itss.model.TimekeepingDetail;
import project.itss.group11.itss.service.IEmployeeDetailTimekeeping;
import project.itss.group11.itss.service.Impl.EmployeeDetailTimekeeping;

import java.time.LocalDateTime;

public class DetailEmployeeViewingController extends WorkspaceController{

    private IEmployeeDetailTimekeeping employeeDetailTimekeeping = new EmployeeDetailTimekeeping();
    ObservableList<TimekeepingDetail> timekeepingDetails = FXCollections.observableArrayList();
    LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 8, 00, 0);
    LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 17, 30, 0);

    @FXML
    private Text username;

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
    private Button backBtn;

    @FXML
    private AnchorPane anchorPane;
    public void initialize(){

        //Constant.pool = ConnectionPool.getInstance("etc/database.config");
        AnchorPane.setTopAnchor(tableview, 100.0);
        AnchorPane.setLeftAnchor(tableview, 10.0);
        AnchorPane.setRightAnchor(tableview, 10.0);
        AnchorPane.setBottomAnchor(tableview, 10.0);

        AnchorPane.setRightAnchor(backBtn, 10.0);

        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        machine.setCellValueFactory(new PropertyValueFactory<>("machine"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        returnEarly.setCellValueFactory(new PropertyValueFactory<>("returnEarly"));
        comeLate.setCellValueFactory(new PropertyValueFactory<>("comeLate"));
        username.setText(username.getText());
        date.setText(EmployeeOverviewController.localDate.getDayOfMonth() + "/"
        + EmployeeOverviewController.localDate.getMonth().getValue() + "/" +
                        EmployeeOverviewController.localDate.getYear()
        );

        timekeepingDetails = employeeDetailTimekeeping.getDetailTimekeepingByDay(EmployeeOverviewController.localDate,startTime,endTime);
        tableview.setItems(timekeepingDetails);


    }
    @FXML
    void backToPrvPage(ActionEvent event) {
        try {
            changeWorkspace("/project/itss/group11/itss/view/XemTQNV.fxml");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at backToPrvPage function at XemChiTietNhanVienController");
        }
    }




}
