package project.itss.group11.itss.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.layout.AnchorPane;

public abstract class WorkspaceView {
	protected AnchorPane mainWorkspaceAnchorPane;
	protected Logger logger = LogManager.getLogger(this.getClass());
	public void setMainWorkspaceAnchorPane(AnchorPane mainWorkspaceAnchorPane) {
		this.mainWorkspaceAnchorPane = mainWorkspaceAnchorPane;
	}
	
	public WorkspaceView() {
		// TODO Auto-generated constructor stub
	}

	public abstract void show();
}
