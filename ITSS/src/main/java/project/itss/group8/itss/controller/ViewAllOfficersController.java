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
import project.itss.group8.itss.helper.Impl.ViewAllOfficersHelperImpl;
import project.itss.group8.itss.helper.ViewAllOfficersHelper;
import project.itss.group8.itss.model.Officer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;

public class ViewAllOfficersController extends WorkspaceController implements Initializable {
    public static int employeeID;
    private LocalDate prevSelectedDate = null;
    ObservableList<Officer> officerObservableList = FXCollections.observableArrayList();
    private static final ViewAllOfficersHelper helper = new ViewAllOfficersHelperImpl();

    @FXML
    private DatePicker date;

    @FXML
    private TableColumn<Officer, String> officerID;

    @FXML
    private TableColumn<Officer, String> officerName;

    @FXML
    private TableColumn<Officer, Integer> officerUnit;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Officer> tableView;

    @FXML
    private TableColumn<Officer, Double> totalFaultHours;

    @FXML
    private TableColumn<Officer, Integer> totalWorkDays;

    @FXML
    private ComboBox<String> unitList;

    @FXML
    private TableColumn<Officer, Integer> workMonth;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Setup Table
        AnchorPane.setTopAnchor(tableView, 120.0);
        AnchorPane.setLeftAnchor(tableView, 10.0);
        AnchorPane.setRightAnchor(tableView, 10.0);
        AnchorPane.setBottomAnchor(tableView, 10.0);
        officerName.setCellValueFactory(new PropertyValueFactory<>("officerName"));
        officerID.setCellValueFactory(new PropertyValueFactory<>("officerID"));
        officerUnit.setCellValueFactory(new PropertyValueFactory<>("officerUnit"));
        workMonth.setCellValueFactory(new PropertyValueFactory<>("workMonth"));
        totalWorkDays.setCellValueFactory(new PropertyValueFactory<>("totalWorkDays"));
        totalFaultHours.setCellValueFactory(new PropertyValueFactory<>("totalFaultHours"));
        officerObservableList = helper.officerObservableList();

        // Set data
        tableView.setItems(officerObservableList);

        // Them listener khi click dup vao 1 row -> ra trang detail?
        tableView.setRowFactory(tableView -> {
            TableRow<Officer> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getClickCount() == 2 && (!row.isEmpty())) {
                    Officer officer = row.getItem();
                    try {
                        employeeID = Integer.parseInt(officer.getOfficerID());
                        changeWorkspace("/project/itss/group8/itss/view/manager/OverViewEmployeeUnit.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        // Them listener cho searchBar
        searchBar.textProperty().addListener((
                (observableValue, oldValue, newValue) -> {
                    FilteredList<Officer> result = new FilteredList<>(officerObservableList);
                    Predicate<Officer> filter;

                    if (newValue.isBlank()){
                        filter = null;
                    } else {
                        Predicate<Officer> constraint1 = o -> o.getOfficerName().contains(newValue);
                        Predicate<Officer> constraint2 = o -> o.getOfficerID().contains(newValue);
                        filter = constraint1.or(constraint2);
                    }

                    result.setPredicate(filter);
                    tableView.setItems(result);
                }
        ));

        // Setup combobox
        setUpComboBoxItems();
        unitList.getSelectionModel().select("All");
        // Them listener cho combobox
        unitList.getSelectionModel().selectedItemProperty().addListener((
                (observableValue, oldValue, newValue) -> {
                    FilteredList<Officer> result = new FilteredList<>(officerObservableList);
                    Predicate<Officer> constraint;

                    if (newValue == "All") constraint = null;
                    else constraint = o -> o.getOfficerUnit() == Integer.parseInt(newValue);

                    result.setPredicate(constraint);
                    tableView.setItems(result);
                }
        ));
    }

    @FXML
    void filterTimekeepingByMonth(ActionEvent event) {
        LocalDate localDate = date.getValue();
        FilteredList<Officer> result = new FilteredList<>(officerObservableList);
        Predicate<Officer> constraint;

        if (localDate != null && prevSelectedDate != localDate) {
            prevSelectedDate = localDate;
            int currentMonth = prevSelectedDate.getMonthValue();
            constraint = o -> o.getWorkMonth() == currentMonth;
        } else constraint = null;

        result.setPredicate(constraint);
        tableView.setItems(result);
    }

    @FXML
    void toWorkerViewClicked(ActionEvent event) {
        try {
            changeWorkspace("/project/itss/group8/itss/view/manager/ViewAllWorkers.fxml");
        }catch (Exception e) {
            logger.error("Error in ToWorkerView");
        }
    }

    private void setUpComboBoxItems() {
        SortedSet<String> items = new TreeSet<>();
        items.add("All");
        for (Officer o : officerObservableList) {
            items.add(String.valueOf(o.getOfficerUnit()));
        }
        unitList.setItems(FXCollections.observableArrayList(items));
    }
}
