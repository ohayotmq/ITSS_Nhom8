package project.itss.group8.itss.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import project.itss.group8.itss.helper.Impl.ViewAllWorkersHelperImpl;
import project.itss.group8.itss.helper.ViewAllWorkersHelper;
import project.itss.group8.itss.model.Worker;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;

public class ViewAllWorkersController extends WorkspaceController implements Initializable {
    ObservableList<Worker> workerObservableList = FXCollections.observableArrayList();
    private static final ViewAllWorkersHelper helper = new ViewAllWorkersHelperImpl();
    private LocalDate prevSelectedDate = null;

    @FXML
    private DatePicker date;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Worker> tableView;

    @FXML
    private TableColumn<Worker, Double> totalOvertime;

    @FXML
    private TableColumn<Worker, Double> totalWorktime;

    @FXML
    private ComboBox<String> unitList;

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
        workerObservableList = helper.workerObservableList();

        // Set up Data
        tableView.setItems(workerObservableList);

        // Add listener cho row
        tableView.setRowFactory(tableView -> {
            TableRow<Worker> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getClickCount() == 2 && (!row.isEmpty())){
//                    try {
//
//                    } catch (IOException e){
//                        e.printStackTrace();
//                    }
                }
            });
            return row;
        });

        // Them listener cho searchBar
        searchBar.textProperty().addListener((
                (observableValue, oldValue, newValue) -> {
                    FilteredList<Worker> result = new FilteredList<>(workerObservableList);
                    Predicate<Worker> filter;

                    if (newValue.isBlank()){
                        filter = null;
                    } else {
                        Predicate<Worker> constraint1 = o -> o.getWorkerName().contains(newValue);
                        Predicate<Worker> constraint2 = o -> o.getWorkerID().contains(newValue);
                        filter = constraint1.or(constraint2);
                    }

                    result.setPredicate(filter);
                    tableView.setItems(result);
                }
        ));

        // Setup ComboBox
        setUpComboBoxItems();
        unitList.getSelectionModel().select("All");
        // Them listener cho combobox
        unitList.getSelectionModel().selectedItemProperty().addListener((
                (observableValue, oldValue, newValue) -> {
                    FilteredList<Worker> result = new FilteredList<>(workerObservableList);
                    Predicate<Worker> constraint;

                    if (newValue == "All") constraint = null;
                    else constraint = o -> o.getWorkerUnit() == Integer.parseInt(newValue);

                    result.setPredicate(constraint);
                    tableView.setItems(result);
                }
        ));
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
    void filterTimekeepingByMonth(ActionEvent event) {
        LocalDate localDate = date.getValue();
        FilteredList<Worker> result = new FilteredList<>(workerObservableList);
        Predicate<Worker> constraint;

        if (localDate != null && prevSelectedDate != localDate) {
            prevSelectedDate = localDate;
            int currentMonth = prevSelectedDate.getMonthValue();
            constraint = w -> w.getWorkMonth() == currentMonth;
        } else constraint = null;

        result.setPredicate(constraint);
        tableView.setItems(result);
    }

    @FXML
    void toOfficerViewClicked(ActionEvent event) {
        try {
            changeWorkspace("/project/itss/group8/itss/view/manager/ViewAllOfficers.fxml");
        }catch (Exception e) {
            logger.error("Error in ToOfficerView");
        }
    }

    private void setUpComboBoxItems() {
        SortedSet<String> items = new TreeSet<>();
        items.add("All");
        for (Worker o : workerObservableList) {
            items.add(String.valueOf(o.getWorkerUnit()));
        }
        unitList.setItems(FXCollections.observableArrayList(items));
    }
}
