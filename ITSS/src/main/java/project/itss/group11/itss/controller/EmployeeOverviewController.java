package project.itss.group11.itss.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import project.itss.group11.itss.Until.ConnectionPool;
import project.itss.group11.itss.Until.Constant;
import project.itss.group11.itss.model.TimekeepingOverview;
import project.itss.group11.itss.service.IEmployeeTimekeepingOverview;
import project.itss.group11.itss.service.Impl.EmployeeTimekeepingOverviewImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeOverviewController extends WorkspaceController {
    public static LocalDate localDate = null;
    IEmployeeTimekeepingOverview employeeTimekeepingOverview = new EmployeeTimekeepingOverviewImpl();
    ObservableList<TimekeepingOverview> timekeepingOverviews = FXCollections.observableArrayList();
    LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 8, 00, 0);
    LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 17, 30, 0);

    @FXML
    private Text userID;
    @FXML
    private Text name;
    @FXML
    private TableColumn<?, ?> comeLate;

    @FXML
    private DatePicker date;

    @FXML
    private TableColumn<TimekeepingOverview, Integer> day;

    @FXML
    private TableColumn<TimekeepingOverview, String> end;

    @FXML
    private Button filter;

    @FXML
    private TableColumn<TimekeepingOverview, Button> requestingBtn;

    @FXML
    private TableColumn<TimekeepingOverview, String> returnEarly;

    @FXML
    private TableColumn<TimekeepingOverview, String> start;

    @FXML
    private TableView<TimekeepingOverview> tableView;

    @FXML
    private TableColumn<TimekeepingOverview, Button> viewingDetailButton;
    @FXML
    private AnchorPane anchorPane;

    public void initialize()
    {   // Mở connect
        //Constant.pool = ConnectionPool.getInstance("etc/database.config");
        // Liên kết các cột với thuộc tính tương ứng trong đối tượng TimekeepingOverview
        AnchorPane.setTopAnchor(tableView, 100.0);
        AnchorPane.setLeftAnchor(tableView, 10.0);
        AnchorPane.setRightAnchor(tableView, 10.0);
        AnchorPane.setBottomAnchor(tableView, 10.0);
        day.setCellValueFactory(new PropertyValueFactory<>("day"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        returnEarly.setCellValueFactory(new PropertyValueFactory<>("returnEarly"));
        comeLate.setCellValueFactory(new PropertyValueFactory<>("comeLate"));
        // set default for selectingDateBtn


        date.setValue(EmployeeOverviewController.localDate);
        name.setText(Constant.employee.getName());
        userID.setText(Constant.employee.getID() + "");




//        date.setValue(LocalDate.now());



        // Tạo cột View Button
        viewingDetailButton.setCellFactory(createButtonCellFactory("View", "view-button"));


        // Tạo cột Request Button
        requestingBtn.setCellFactory(createButtonCellFactory("Request", "request-button"));



        // Đổ dữ liệu từ ObservableList vào TableView

        if (EmployeeOverviewController.localDate != null)
            timekeepingOverviews = employeeTimekeepingOverview.getTimekeepingByMonth(EmployeeOverviewController.localDate,startTime,endTime);
        tableView.setItems(timekeepingOverviews);

//        timekeepingOverviews = employeeTimekeepingOverview.getTimekeepingByMonth(EmployeeOverviewController.localDate,startTime,endTime);
//        tableView.setItems(timekeepingOverviews);

    }
    private Callback<TableColumn<TimekeepingOverview, Button>, TableCell<TimekeepingOverview, Button>> createButtonCellFactory(String buttonText, String buttonStyleClass) {
        return column -> new TableCell<TimekeepingOverview, Button>() {
            private final Button button = new Button(buttonText);

            {
                button.getStyleClass().add(buttonStyleClass);
                button.setOnAction(event -> {
                    TimekeepingOverview timekeepingOverview = getTableRow().getItem();
                    if(buttonStyleClass.equals("view-button"))
                    {
                        TimekeepingOverview timekeepingOverview1 = getTableRow().getItem();
                        localDate = LocalDate.of(date.getValue().getYear(),date.getValue().getMonth(),timekeepingOverview1.getDay());
                        // Lấy đối tượng Stage hiện tại
                        try {
							changeWorkspace("/project/itss/group11/itss/view/XemChiTietNV.fxml");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                    else System.out.println("request button");
                });
            }
            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        };
    }


    @FXML
    void filterTimekeepingByMonth(ActionEvent event) {
        // Lấy giá trị ngày từ DatePicker
        LocalDate selectedDate = date.getValue();
        if (selectedDate==null)
        {
            EmployeeOverviewController.localDate = LocalDate.now();
            date.setValue(LocalDate.now());




        }
        else
            EmployeeOverviewController.localDate = selectedDate;

        timekeepingOverviews = employeeTimekeepingOverview.getTimekeepingByMonth(EmployeeOverviewController.localDate,startTime,endTime);
        tableView.setItems(timekeepingOverviews);
    }
    
	public void setMainWorkspaceAnchorPane(AnchorPane mainWorkspaceAnchorPane) {
		this.mainWorkspaceAnchorPane = mainWorkspaceAnchorPane;
	}
    
    
}
