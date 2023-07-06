package project.itss.group11.itss.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import project.itss.group11.itss.Until.Constant;
import project.itss.group11.itss.model.Employee;
import project.itss.group11.itss.model.TimekeepingOverview;
import project.itss.group11.itss.service.Impl.SearchInfEmployeeUnitServiceImpl;
import project.itss.group11.itss.service.SearchInfEmployeeUnitService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchInfEmployeeUnitController extends WorkspaceController implements Initializable {

    public static int employeeID;

    private static final SearchInfEmployeeUnitService service = new SearchInfEmployeeUnitServiceImpl();

    @FXML
    private TextField ID_Employee;

    @FXML
    private TableColumn<Employee, Integer> id;

    @FXML
    private TableColumn<Employee, String> name;

    @FXML
    private Button search;

    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, Button> view;

    ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchorPane;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setTopAnchor(tableView, 120.0);
        AnchorPane.setLeftAnchor(tableView, 10.0);
        AnchorPane.setRightAnchor(tableView, 10.0);
        AnchorPane.setBottomAnchor(tableView, 10.0);
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        view.setCellFactory(createButtonCellFactory("View", "view-button"));
        employeeObservableList = service.employeeObservableList(Constant.employee.getUnit());
        tableView.setItems(employeeObservableList);
    }
    @FXML

    void onSearchEmplyee(ActionEvent event) {
        if(ID_Employee!=null){
            try {
                employeeID = Integer.parseInt(ID_Employee.getText());
                changeWorkspace("/project/itss/group11/itss/view/manager/OverViewEmployeeUnit.fxml");
            }catch (Exception e){
                logger.error("Error when searchEmployee: ", e);
            }
        }
    }

    private Callback<TableColumn<Employee, Button>, TableCell<Employee, Button>> createButtonCellFactory(String buttonText, String buttonStyleClass){
        return column -> new TableCell<Employee, Button>() {
            private final Button button = new Button(buttonText);

            {
                button.getStyleClass().add(buttonStyleClass);
                button.setOnAction(event -> {
                    Employee employee = getTableRow().getItem();
                    // Lấy đối tượng Stage hiện tại
                        try {
                            employeeID = employee.getID();
                            changeWorkspace("/project/itss/group11/itss/view/manager/OverViewEmployeeUnit.fxml");
                        } catch (IOException e) {
                            logger.error("Error in createButtonCellFactory ", e);
                        }

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


}
