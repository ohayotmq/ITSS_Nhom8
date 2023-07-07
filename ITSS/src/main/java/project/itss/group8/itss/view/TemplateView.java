package project.itss.group8.itss.view;

import javafx.scene.paint.Color;
import project.itss.group8.itss.GUIStarter;
import project.itss.group8.itss.utils.Constant;
import project.itss.group8.itss.controller.WorkspaceController;
import project.itss.group8.itss.controller.TemplateController;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

// Cac view sau khi login can extends TemplateView de hien thi header va sidebar
public abstract class TemplateView {
	protected Logger logger = LogManager.getLogger(this.getClass());
	protected Scene scene = new Scene(new Button("dummy node"));
	protected Stage stage;
	protected TemplateController templateController = new TemplateController(this);
	
	//workspace (la phan pane trống ben phai)
	protected AnchorPane mainWorkspaceAnchorPane;
	
	public void init() {
		FXMLLoader rootFxmlLoader = new FXMLLoader(getClass().getResource("template.fxml"));
		rootFxmlLoader.setController(templateController);
		Parent root = null;
		try {
			root = rootFxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("template.css").toExternalForm());
		stage.setScene(scene);
	}
	
	public void showHome() {
		stage.setTitle("Phần mềm chấm công ");
		stage.setScene(scene);
		stage.show();
    
		mainWorkspaceAnchorPane = (AnchorPane)(scene.lookup("#mainWorkspaceAnchorPane"));
		MenuButton dropdownMenuButton = (MenuButton)(scene.lookup("#dropdownMenuButton"));
		MenuItem logoutMenuItem = dropdownMenuButton.getItems().get(1);
		logoutMenuItem.setOnAction(event -> {
			Constant.employee = null;
			FXMLLoader fxmlLoader = new FXMLLoader(GUIStarter.class.getResource("Login.fxml"));
	        Scene scene = null;
			try {
				scene = new Scene(fxmlLoader.load());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        stage.setTitle("Hello!");
	        stage.setScene(scene);
	        stage.show();
		});

	}
	
	public Button createOptionButton(String option) {
		Button btn = new Button();
		btn.setTextAlignment(TextAlignment.CENTER);
		btn.setStyle("-fx-background-color: #657ef8;");
		btn.setTextFill(Color.WHITE);
		btn.setWrapText(true);
		btn.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
		btn.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
		btn.setMaxSize(Double.MAX_VALUE, 72);
		btn.setText(option);
		option = option.replaceAll(" ", "-");   
		btn.setId(option);
		return btn;
	}
	
	public void addOptionButton(Button btn) {
		stage.setScene(scene);
		VBox mainOptionVBox = (VBox)(scene.lookup("#mainOptionVBox"));
		VBox.setVgrow(btn, Priority.ALWAYS);
		mainOptionVBox.getChildren().add(btn);
	}
	
	public void addToWorkspace(String fxmlPath) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));		
		
		// init workspace
		Parent workspaceRoot = fxmlLoader.load();
		AnchorPane.setTopAnchor(workspaceRoot, 0.0);
		AnchorPane.setBottomAnchor(workspaceRoot, 0.0);
		AnchorPane.setRightAnchor(workspaceRoot, 0.0);
		AnchorPane.setLeftAnchor(workspaceRoot, 0.0);
		mainWorkspaceAnchorPane.getChildren().clear();
		mainWorkspaceAnchorPane.getChildren().add(workspaceRoot);
		WorkspaceController workspaceController = fxmlLoader.getController();
		if(workspaceController != null)
				workspaceController.setMainWorkspaceAnchorPane(mainWorkspaceAnchorPane);
		else
			logger.info(fxmlPath + " has no controller or load controller faled");
	}
	
	public void addToWorkspace(WorkspaceView workspaceView) {
		workspaceView.setMainWorkspaceAnchorPane(mainWorkspaceAnchorPane);
		workspaceView.show();
	}
	
	public abstract void show() throws IOException;
	
	public TemplateView(Stage stage) {
		this.stage = stage;
	}
	
	public TemplateView(){
	}
	
	// Can add listener cho logout va notification
}