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
import project.itss.group11.itss.model.TimekeepingOverview;
import project.itss.group11.itss.service.IEmployeeTimekeepingOverview;
import project.itss.group11.itss.service.Impl.EmployeeTimekeepingOverviewImpl;
import project.itss.group11.itss.service.Impl.LoginServiceImpl;
import project.itss.group11.itss.service.Impl.OverViewInforEmployeeUnitServiceImpl;
import project.itss.group11.itss.service.LoginService;
import project.itss.group11.itss.service.OverViewInforEmployeeUnitService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OverViewInforEmployeeUnitController extends WorkspaceController {
    public static LocalDate localDate = null;
    private static final LoginService loginService = new LoginServiceImpl();
    private static final OverViewInforEmployeeUnitService service = new OverViewInforEmployeeUnitServiceImpl();
    ObservableList<TimekeepingOverview> timekeepingOverviews = FXCollections.observableArrayList();
    LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 8, 00, 0);
    LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 17, 30, 0);

    @FXML
    private Text username;

    @FXML
    private Text time;

    @FXML
    private Text id_Employee;

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
    {
        AnchorPane.setTopAnchor(tableView, 120.0);
        AnchorPane.setLeftAnchor(tableView, 10.0);
        AnchorPane.setRightAnchor(tableView, 10.0);
        AnchorPane.setBottomAnchor(tableView, 10.0);
        username.setText(loginService.getUserInfor(SearchInfEmployeeUnitController.employeeID).getName());
        id_Employee.setText(String.valueOf(SearchInfEmployeeUnitController.employeeID));
        time.setText(LocalDate.now().toString());
        day.setCellValueFactory(new PropertyValueFactory<>("day"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        returnEarly.setCellValueFactory(new PropertyValueFactory<>("returnEarly"));
        comeLate.setCellValueFactory(new PropertyValueFactory<>("comeLate"));

        date.setValue(OverViewInforEmployeeUnitController.localDate);

        viewingDetailButton.setCellFactory(createButtonCellFactory("View", "view-button"));

        requestingBtn.setCellFactory(createButtonCellFactory("Request", "request-button"));

        if (OverViewInforEmployeeUnitController.localDate != null)
            timekeepingOverviews = service.getTimekeepingByMonth(OverViewInforEmployeeUnitController.localDate,startTime,endTime,SearchInfEmployeeUnitController.employeeID);
        tableView.setItems(timekeepingOverviews);
    }
    private Callback<TableColumn<TimekeepingOverview, Button>, TableCell<TimekeepingOverview, Button>> createButtonCellFactory(String buttonText, String buttonStyleClass) {
        return column -> new TableCell<TimekeepingOverview, Button>() {
            private final Button button = new Button(buttonText);

            {
                button.getStyleClass().add(buttonStyleClass);
                button.setOnAction(event -> {
                    if(buttonStyleClass.equals("view-button"))
                    {
                        TimekeepingOverview timekeepingOverview1 = getTableRow().getItem();
                        localDate = LocalDate.of(date.getValue().getYear(),date.getValue().getMonth(),timekeepingOverview1.getDay());
                        try {
                            changeWorkspace("/project/itss/group11/itss/view/manager/DetailEmployeeUnit.fxml");
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
        LocalDate selectedDate = date.getValue();
        if (selectedDate==null)
        {
            OverViewInforEmployeeUnitController.localDate = LocalDate.now();
            date.setValue(LocalDate.now());
        }
        else
            OverViewInforEmployeeUnitController.localDate = selectedDate;

        timekeepingOverviews = service.getTimekeepingByMonth(OverViewInforEmployeeUnitController.localDate,startTime,endTime,SearchInfEmployeeUnitController.employeeID);
        tableView.setItems(timekeepingOverviews);
    }

    public void setMainWorkspaceAnchorPane(AnchorPane mainWorkspaceAnchorPane) {
        this.mainWorkspaceAnchorPane = mainWorkspaceAnchorPane;
    }
    @FXML
    void backToPreviousPage(ActionEvent event) {
        try {
            changeWorkspace("/project/itss/group11/itss/view/manager/ViewEmployeeUnit.fxml");
        }catch (Exception e) {
            logger.error("Error in BacktoPreviousPage");
        }
    }
}
