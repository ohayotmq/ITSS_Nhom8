package project.itss.group11.itss.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import project.itss.group11.itss.view.TemplateView;


// Se chua method de xu li notification va dropdown menu
public class TemplateController extends BaseController{
	TemplateView templateView;
	
	public TemplateController(TemplateView templateView) {
		this.templateView = templateView;
	}
	
}
