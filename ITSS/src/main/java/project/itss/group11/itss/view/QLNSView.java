package project.itss.group11.itss.view;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import project.itss.group11.itss.controller.ImportFileChamCongController;
import project.itss.group11.itss.controller.TemplateController;
import project.itss.group11.itss.model.LogInfor;
import project.itss.group11.itss.model.PreviewFileChamCongTableRowModel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

// Tao view khac: copy y het class nay ve, chi can doc huong dan -----------huong dan----------- o duoi

//------------------- sua ten class (vd OfficeView) -------------------------------
public class QLNSView extends TemplateView{
	
//------------- Them controller cua minh -----------------	
	//private ImportFileChamCongController importFileChamCongController = new ImportFileChamCongController(this);
	
	public QLNSView(Stage stage) {
		this.stage = stage;
	}
	
	
	public void init() {
		FXMLLoader rootFxmlLoader = new FXMLLoader(getClass().getResource("template.fxml"));
		rootFxmlLoader.setController(templateController);
		Parent root = null;
		try {
			root = rootFxmlLoader.load();
		} catch (IOException e) {
			logger.error("Error in init: ",e);
		}
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("template.css").toExternalForm());
	}
	
	public void show() throws IOException {
		init();
		// init workspace
		showHome();
		
		Button btn1 = createOptionButton("Thông tin chấm công của tôi");
                btn1.setOnMouseClicked(event -> {
			try {
				addToWorkspace("XemTQNV.fxml");
			} catch (IOException e) {
				logger.error("Error in Employee view", e);
			}
		});
		Button btn2 = createOptionButton("Xem thông tin chấm công của nhân viên");
                btn2.setOnMouseClicked(event -> {
			try {
				addToWorkspace("/project/itss/group11/itss/view/manager/ViewEmployeeUnit.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Button btn3 = createOptionButton("Xem yêu cầu chỉnh sửa chấm công");
		btn3.setOnMouseClicked(event -> {
			try {
				addToWorkspace("/project/itss/group11/itss/EditCC1.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
                Button importOptionButton = createOptionButton("Import file chấm công");
		importOptionButton.setOnMouseClicked(event -> {
			addToWorkspace(new ImportFileChamCongView());
		});
		addOptionButton(btn1);
		addOptionButton(btn2);
		addOptionButton(btn3);
		addOptionButton(importOptionButton);
	}
	
	
}
