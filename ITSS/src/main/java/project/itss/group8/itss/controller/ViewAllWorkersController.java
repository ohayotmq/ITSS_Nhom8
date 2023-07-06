package project.itss.group8.itss.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import project.itss.group8.itss.helper.Impl.ViewAllWorkersHelperImpl;
import project.itss.group8.itss.helper.ViewAllWorkersHelper;
import project.itss.group8.itss.model.Worker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewAllWorkersController extends WorkspaceController implements Initializable {
    ObservableList<Worker> workerObservableList = FXCollections.observableArrayList();
    private static final ViewAllWorkersHelper helper = new ViewAllWorkersHelperImpl();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private DatePicker date;

    @FXML
    private Button filter;

    @FXML
    private Text name;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Worker> tableView;

    @FXML
    private Button toWorkerView;

    @FXML
    private TableColumn<Worker, Double> totalOvertime;

    @FXML
    private TableColumn<Worker, Double> totalWorktime;

    @FXML
    private Label unit;

    @FXML
    private ComboBox<?> unitList;

    @FXML
    private TableColumn<Worker, Button> viewDetailBtn;

    @FXML
    private TableColumn<Worker, Integer> workMonth;

    @FXML
    private TableColumn<Worker, String> workerID;

    @FXML
    private TableColumn<Worker, String> workerName;

    @FXML
    private TableColumn<Worker, Integer> workerUnit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setTopAnchor(tableView, 120.0);
        AnchorPane.setLeftAnchor(tableView, 10.0);
        AnchorPane.setRightAnchor(tableView, 10.0);
        AnchorPane.setBottomAnchor(tableView, 10.0);
        workerName.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        workerID.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        workerUnit.setCellValueFactory(new PropertyValueFactory<>("workerUnit"));
        workMonth.setCellValueFactory(new PropertyValueFactory<>("workMonth"));
        totalWorktime.setCellValueFactory(new PropertyValueFactory<>("workerTotalWorkHour"));
        totalOvertime.setCellValueFactory(new PropertyValueFactory<>("workerTotalOvertimeHour"));
        viewDetailBtn.setCellFactory(createButtonCellFactory("View", "view-button"));
        workerObservableList = helper.workerObservableList();
        // this.setUpWorkerItems();
        tableView.setItems(workerObservableList);
    }

    void onSearchWorker(ActionEvent event) {
        if(searchBar != null){
            try {
                String filterValue = searchBar.getText();
            }catch (Exception e){
                logger.error("Error when search workers: ", e);
            }
        }
    }

    private Callback<TableColumn<Worker, Button>, TableCell<Worker, Button>> createButtonCellFactory(String buttonText, String buttonStyleClass){
        return column -> new TableCell<Worker, Button>() {
            private final Button button = new Button(buttonText);

            {
                button.getStyleClass().add(buttonStyleClass);
                button.setOnAction(event -> {
                    Worker worker = getTableRow().getItem();
                    // Lấy đối tượng Stage hiện tại
                    try {
                        String workerName = worker.getWorkerName();
                        changeWorkspace("/project/itss/group8/itss/view/manager/OverViewEmployeeUnit.fxml");
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

    private void setUpWorkerItems() {
        Worker worker1 = new Worker(
                "Ramy",
                "CN123",
                1,
                2,
                16.0,
                15.0
        );
        Worker worker2 = new Worker(
                "Ramu",
                "CN124",
                1,
                2,
                16.0,
                15.0
        );
        Worker worker3 = new Worker(
                "Ram",
                "CN125",
                1,
                2,
                16.0,
                15.0
        );

        workerObservableList.add(worker1);
        workerObservableList.add(worker2);
        workerObservableList.add(worker3);
    }

    @FXML
    void backToPreviousPage(ActionEvent event) {

    }

    @FXML
    void filterTimekeepingByMonth(ActionEvent event) {

    }
}
