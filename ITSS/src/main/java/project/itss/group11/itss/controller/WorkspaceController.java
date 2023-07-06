package project.itss.group11.itss.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

// Cac controller cua phan workspace can extends class nay
public abstract class WorkspaceController extends BaseController {

	protected AnchorPane mainWorkspaceAnchorPane;
	protected Logger logger = LogManager.getLogger(this.getClass());
	public void setMainWorkspaceAnchorPane(AnchorPane mainWorkspaceAnchorPane) {
		this.mainWorkspaceAnchorPane = mainWorkspaceAnchorPane;
	}
	
	public void changeWorkspace(String fxmlPath) throws IOException {
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
}
