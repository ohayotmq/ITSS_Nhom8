package project.itss.group8.itss.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import project.itss.group8.itss.helper.Impl.ViewAllWorkersHelperImpl;
import project.itss.group8.itss.helper.ViewAllWorkersHelper;
import project.itss.group8.itss.model.Worker;
import project.itss.group8.itss.helper.Impl.OpenFileLocation;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class ExportAllWorkersController extends WorkspaceController implements Initializable {
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

    @FXML
    private Button setFileLocation;

    @FXML
    private Text pathLocation;

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
            TableView.TableViewSelectionModel<Worker> selectionModel = tableView.getSelectionModel();
            ObservableList<TablePosition> selectedCells = selectionModel.getSelectedCells();

            writer.write('\ufeff');

            // Write table headers
            TableColumn<Worker, ?>[] columns = new TableColumn[tableView.getColumns().size()];
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
            for (Worker worker : tableView.getItems()) {
                for (int i = 0; i < columns.length; i++) {
                    TableColumn<Worker, ?> column = columns[i];
                    Object cellData = column.getCellData(worker);
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
        TableColumn<Worker, ?>[] columns = new TableColumn[tableView.getColumns().size()];
        columns = tableView.getColumns().toArray(columns);
        for (int i = 0; i < columns.length; i++) {
            org.apache.poi.ss.usermodel.Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(columns[i].getText());
        }

        // Write table data
        ObservableList<Worker> items = tableView.getItems();
        for (int rowIdx = 0; rowIdx < items.size(); rowIdx++) {
            Worker worker = items.get(rowIdx);
            Row row = sheet.createRow(rowIdx + 1);
            for (int colIdx = 0; colIdx < columns.length; colIdx++) {
                TableColumn<Worker, ?> column = columns[colIdx];
                Cell cell = row.createCell(colIdx);
                Object cellData = column.getCellData(worker);
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
