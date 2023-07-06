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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import project.itss.group8.itss.helper.Impl.OpenFileLocation;
import project.itss.group8.itss.helper.Impl.ViewAllOfficersHelperImpl;
import project.itss.group8.itss.helper.ViewAllOfficersHelper;
import project.itss.group8.itss.model.Officer;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;

public class ExportAllOfficersController extends WorkspaceController implements Initializable {
    public static int employeeID;
    private LocalDate prevSelectedDate = null;
    ObservableList<Officer> officerObservableList = FXCollections.observableArrayList();
    private static final ViewAllOfficersHelper helper = new ViewAllOfficersHelperImpl();

    @FXML
    private DatePicker date;

    @FXML
    private TableColumn<Officer, String> officerID;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableColumn<Officer, String> officerName;

    @FXML
    private TableColumn<Officer, Integer> officerUnit;

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

    @FXML
    private Text pathLocation;

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
    void toExportWorkerViewClicked(ActionEvent event) {
        try {
            changeWorkspace("/project/itss/group8/itss/view/manager/ExportAllWorkers.fxml");
        } catch (Exception e) {
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

    @FXML
    void changeFileLocation(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export to CSV");


//         Set the initial directory (optional)
        fileChooser.setInitialDirectory(new File("C:/"));

        // Set the file extension filters (optional)
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        FileChooser.ExtensionFilter excelExtensionFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().addAll(extensionFilter, excelExtensionFilter);

        // Show the file chooser dialog
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            String filePath = file.getAbsolutePath();
            pathLocation.setText(filePath);

            // Add a listener to the selected extension filter property
            fileChooser.selectedExtensionFilterProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    String newExtension = newValue.getExtensions().get(0);
                    String newFilePath = file.getAbsolutePath().substring(0, filePath.lastIndexOf('.')) + newExtension;
                    pathLocation.setText(newFilePath);
                }
            });
        }
    }

    @FXML
    void exportToCSV(ActionEvent event) {
        String filePath = pathLocation.getText();
        File file = new File(filePath);

        if (file != null) {
            try {
                String extension = getFileExtension(file.getName());
                if (extension.equalsIgnoreCase("csv")) {
                    exportToCSV(file);
                } else if (extension.equalsIgnoreCase("xlsx")) {
                    exportToExcel(file);
                } else {
                    System.out.println("Unsupported file extension: " + extension);
                }

                // Open the folder containing the exported file
                OpenFileLocation fileLocationOpener = new OpenFileLocation();
                fileLocationOpener.openFileLocation(file.getParentFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void exportToCSV(File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            TableView.TableViewSelectionModel<Officer> selectionModel = tableView.getSelectionModel();
            ObservableList<TablePosition> selectedCells = selectionModel.getSelectedCells();

            writer.write('\ufeff');

            // Write table headers
            TableColumn<Officer, ?>[] columns = new TableColumn[tableView.getColumns().size()];
            columns = tableView.getColumns().toArray(columns);
            for (int i = 0; i < columns.length; i++) {
                writer.print(columns[i].getText());
                if (i < columns.length - 1) {
                    writer.print(",");
                } else {
                    writer.println();
                }
            }

            // Write table data
            for (Officer officer : tableView.getItems()) {
                for (int i = 0; i < columns.length; i++) {
                    TableColumn<Officer, ?> column = columns[i];
                    Object cellData = column.getCellData(officer);
                    writer.print(cellData != null ? cellData.toString() : "");
                    if (i < columns.length - 1) {
                        writer.print(",");
                    } else {
                        writer.println();
                    }
                }
            }

            System.out.println("Data exported to: " + file.getAbsolutePath());
        }
    }

    private void exportToExcel(File file) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // Write table headers
        Row headerRow = sheet.createRow(0);
        TableColumn<Officer, ?>[] columns = new TableColumn[tableView.getColumns().size()];
        columns = tableView.getColumns().toArray(columns);
        for (int i = 0; i < columns.length; i++) {
            org.apache.poi.ss.usermodel.Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(columns[i].getText());
        }

        // Write table data
        ObservableList<Officer> items = tableView.getItems();
        for (int rowIdx = 0; rowIdx < items.size(); rowIdx++) {
            Officer officer = items.get(rowIdx);
            Row row = sheet.createRow(rowIdx + 1);
            for (int colIdx = 0; colIdx < columns.length; colIdx++) {
                TableColumn<Officer, ?> column = columns[colIdx];
                Cell cell = row.createCell(colIdx);
                Object cellData = column.getCellData(officer);
                cell.setCellValue(cellData != null ? cellData.toString() : "");
            }
        }
        try (OutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
        }

        workbook.close();

        // Open the folder containing the exported file
        openFileLocation(file.getParentFile());
        System.out.println("Data exported to: " + file.getAbsolutePath());
    }
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
    }
    private void openFileLocation(File folder) {
        try {
            Desktop.getDesktop().open(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
