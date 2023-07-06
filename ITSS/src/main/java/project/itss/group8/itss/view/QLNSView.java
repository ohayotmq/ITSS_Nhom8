package project.itss.group8.itss.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

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
		
		Button btn1 = createOptionButton("Export file chấm công");
                btn1.setOnMouseClicked(event -> {
			try {
				addToWorkspace("/project/itss/group8/itss/view/manager/ExportAllOfficers.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		Button btn2 = createOptionButton("Xem danh sách nhân viên");
                btn2.setOnMouseClicked(event -> {
			try {
				addToWorkspace("/project/itss/group8/itss/view/manager/ViewEmployeeUnit.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Button btn3 = createOptionButton("Xem thông tin chấm công tổng hợp");
		btn3.setOnMouseClicked(event -> {
			try {
				addToWorkspace("/project/itss/group8/itss/view/manager/ViewAllOfficers.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Button btn4 = createOptionButton("Xem yêu cầu chỉnh sửa chấm công");
		btn4.setOnMouseClicked(event -> {
			try {
				addToWorkspace("/project/itss/group8/itss/EditCC1.fxml");
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
		addOptionButton(btn4);
		addOptionButton(importOptionButton);
	}
	
	
}
