package project.itss.group8.itss.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import project.itss.group8.itss.model.Form;
import project.itss.group8.itss.helper.IFormOverviewService;
import project.itss.group8.itss.helper.Impl.FormOverviewServiceImpl;
import java.io.IOException;
import java.time.LocalDateTime;

public class EditCC1Controller extends WorkspaceController{
    public Form form1;
    IFormOverviewService formOverviewService =  new FormOverviewServiceImpl();
    ObservableList<Form> dataList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Form, Integer> idnv;
    @FXML
    private TableColumn<Form, LocalDateTime> oldT;
    @FXML
    private TableColumn<Form, LocalDateTime> newT;
    @FXML
    private TableColumn<Form, Integer> oldD;
    @FXML
    private TableColumn<Form, Integer> newD;
    @FXML
    private TableColumn<Form, Button> updateButton;

    @FXML
    private TableView<Form> tableView;

    public void initialize()
    {
    	System.out.println("Init EditCC1");
        idnv.setCellValueFactory(new PropertyValueFactory<>("idnv"));
        oldT.setCellValueFactory(new PropertyValueFactory<>("oldT"));
        newT.setCellValueFactory(new PropertyValueFactory<>("newT"));
        oldD.setCellValueFactory(new PropertyValueFactory<>("oldDevice"));
        newD.setCellValueFactory(new PropertyValueFactory<>("newDevice"));
        updateButton.setCellFactory(createButtonCellFactory("Update", "update-button"));
        dataList = formOverviewService.getFormData();
        tableView.setItems(dataList);
        System.out.println("Init EditCC1");
    }

    private Callback<TableColumn<Form, Button>, TableCell<Form, Button>> createButtonCellFactory(String buttonText, String buttonStyleClass) {
        return column -> new TableCell<Form, Button>() {
            private final Button button = new Button(buttonText);

            {
                button.getStyleClass().add(buttonStyleClass);
                button.setOnAction(event -> {
                    form1 = getTableRow().getItem();
                    if (form1 != null) {
                        UpdateController.setForm(form1);
                        try {
							changeWorkspace("/project/itss/group8/itss/view/Update.fxml");
						} catch (IOException e) {
							e.printStackTrace();
						}
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
