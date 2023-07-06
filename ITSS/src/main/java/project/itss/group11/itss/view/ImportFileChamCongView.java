package project.itss.group11.itss.view;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import project.itss.group11.itss.controller.ImportFileChamCongController;
import project.itss.group11.itss.model.PreviewFileChamCongTableRowModel;

public class ImportFileChamCongView extends WorkspaceView{
	private ImportFileChamCongController importFileChamCongController = new ImportFileChamCongController(this);
	
	public ImportFileChamCongView() {
		// TODO Auto-generated constructor stub
	}
	
	public File showFileChooser(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Chon file cham cong");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("File cham cong","*.csv"));
		File file = fileChooser.showOpenDialog(new Stage());
		return file;	
	}
	
	public void showImportFileChamCongWorkspace() {
		System.out.println("Show workspace");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("importFileChamCongWorkspace.fxml"));
		VBox workspaceVbox = null;
		try {
			workspaceVbox = fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// init workspace
		AnchorPane.setTopAnchor(workspaceVbox, 0.0);
		AnchorPane.setBottomAnchor(workspaceVbox, 0.0);
		AnchorPane.setRightAnchor(workspaceVbox, 0.0);
		AnchorPane.setLeftAnchor(workspaceVbox, 0.0);
		mainWorkspaceAnchorPane.getChildren().clear();
		mainWorkspaceAnchorPane.getChildren().add(workspaceVbox);
		
		// cai dat listener cho chon file khac button
		Button chooseAnotherFileButton = (Button)(mainWorkspaceAnchorPane.lookup("#chooseAnotherFileButton"));
		chooseAnotherFileButton.setOnMouseClicked(event ->{
			System.out.println("Choose another file");
			File file = showFileChooser();
			importFileChamCongController.handleCsvInput(file);
			showImportFileChamCongWorkspace();
			importFileChamCongController.handleShowTable();
		});
		System.out.println("Showed workspace");
	}
	
	public void showTable(ObservableList<PreviewFileChamCongTableRowModel> tableRows, ArrayList<Boolean> isDuplicate) {
		System.out.println("Show table");
		System.out.println("Table contains " + tableRows.size() + " row(s)");
		mainWorkspaceAnchorPane.applyCss();
		mainWorkspaceAnchorPane.layout();
		TableView<PreviewFileChamCongTableRowModel> table = (TableView<PreviewFileChamCongTableRowModel>)(mainWorkspaceAnchorPane.lookup("#importFileChamCongTable"));
		
		TableColumn idColumn = table.getColumns().get(0);
		TableColumn timeColumn = table.getColumns().get(1);
		TableColumn deviceColumn = table.getColumns().get(2);
		TableColumn nameColumn = table.getColumns().get(3);
		TableColumn roleColumn = table.getColumns().get(4);
		TableColumn unitColumn = table.getColumns().get(5);
		TableColumn birthdateColumn = table.getColumns().get(6);
		TableColumn genderColumn = table.getColumns().get(7);
		TableColumn selectColumn = table.getColumns().get(8);
		idColumn.setCellValueFactory(new PropertyValueFactory<PreviewFileChamCongTableRowModel, Integer>("id"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<PreviewFileChamCongTableRowModel, LocalDateTime>("timestamp"));
		deviceColumn.setCellValueFactory(new PropertyValueFactory<PreviewFileChamCongTableRowModel, Integer>("device"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<PreviewFileChamCongTableRowModel, String>("name"));
		roleColumn.setCellValueFactory(new PropertyValueFactory<PreviewFileChamCongTableRowModel, String>("role"));
		unitColumn.setCellValueFactory(new PropertyValueFactory<PreviewFileChamCongTableRowModel, String>("unit"));
		birthdateColumn.setCellValueFactory(new PropertyValueFactory<PreviewFileChamCongTableRowModel, LocalDate>("gender"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<PreviewFileChamCongTableRowModel, String>("birtdate"));
		selectColumn.setCellValueFactory(new PropertyValueFactory<PreviewFileChamCongTableRowModel, CheckBox>("selectCheckBox"));
		
		table.setItems(tableRows);
		for(int i=0; i< table.getItems().size(); i++) {
			if(isDuplicate.get(i)) {
				CheckBox checkBox = table.getItems().get(i).getSelectCheckBox();
				checkBox.setDisable(true);
				checkBox.setText("is duplicated");
				
			}
		}
		
		// cai dat listener cho sellect all checkbox
		CheckBox selectAllCheckBox = (CheckBox)(mainWorkspaceAnchorPane.lookup("#selectAllCheckBox"));	
		selectAllCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				ObservableList<PreviewFileChamCongTableRowModel> rows = table.getItems();
				if(selectAllCheckBox.isSelected()) {
					for(PreviewFileChamCongTableRowModel row: rows) {
						if(row.getSelectCheckBox().isDisabled())
							continue;
						row.getSelectCheckBox().setSelected(true);
					}
				}else {
					for(PreviewFileChamCongTableRowModel row: rows) {
						row.getSelectCheckBox().setSelected(false);
					}
				}
				
			}
		});;
		
		// cai dat listener import button
		Button importButton = (Button)(mainWorkspaceAnchorPane.lookup("#importButton"));
		importButton.setOnMouseClicked(event -> {
			ArrayList<Boolean> isSelected = new ArrayList<Boolean>();
			for(int i=0; i< table.getItems().size(); i++) {
				CheckBox checkBox = table.getItems().get(i).getSelectCheckBox();
				if(checkBox.isSelected()) {
					System.out.println(i);
					isSelected.add(true);
				}else
					isSelected.add(false);
			}
			Boolean isSuccess = importFileChamCongController.handleImport(isSelected);
			if(isSuccess) {
				// Chuyen toi man hinh thong bao thanh cong
				VBox vbox = new VBox();
				vbox.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
				vbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				Label label = new Label("Successfully imported!");
				vbox.getChildren().add(label);
				vbox.setAlignment(Pos.CENTER);
				AnchorPane.setTopAnchor(vbox, 0.0);
				AnchorPane.setBottomAnchor(vbox, 0.0);
				AnchorPane.setRightAnchor(vbox, 0.0);
				AnchorPane.setLeftAnchor(vbox, 0.0);
				mainWorkspaceAnchorPane.getChildren().clear();
				mainWorkspaceAnchorPane.getChildren().add(vbox);
				mainWorkspaceAnchorPane.applyCss();
				mainWorkspaceAnchorPane.layout();
			}else {
				// Chuyen toi man hinh thong bao thanh cong
				VBox vbox = new VBox();
				vbox.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
				vbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				Label label = new Label("Failed to import!");
				vbox.getChildren().add(label);
				vbox.setAlignment(Pos.CENTER);
				AnchorPane.setTopAnchor(vbox, 0.0);
				AnchorPane.setBottomAnchor(vbox, 0.0);
				AnchorPane.setRightAnchor(vbox, 0.0);
				AnchorPane.setLeftAnchor(vbox, 0.0);
				mainWorkspaceAnchorPane.getChildren().clear();
				mainWorkspaceAnchorPane.getChildren().add(vbox);
				mainWorkspaceAnchorPane.applyCss();
				mainWorkspaceAnchorPane.layout();
			}
		});
		
		System.out.println("Showed table");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		File file = showFileChooser();
		importFileChamCongController.handleCsvInput(file);
		showImportFileChamCongWorkspace();
		importFileChamCongController.handleShowTable();
	}
	
	
}
