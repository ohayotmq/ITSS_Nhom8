package project.itss.group8.itss.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import project.itss.group8.itss.utils.Constant;
import project.itss.group8.itss.model.Employee;

import java.net.URL;
import java.util.ResourceBundle;
//no longer need this
public class AdminTemplateController extends BaseController implements Initializable {

    @FXML
    private Label id ;

    @FXML
    private Text name;

    @FXML
    private Employee user;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = Constant.employee;
        id.setText(String.valueOf(user.getID()));
        name.setText(user.getName());
    }

    @FXML
    void selectViewingTimekeepingScreen(ActionEvent event) {
        
    }

    @FXML
    void selectEditTimekeepingScreen(ActionEvent event) {
        try {
           // changeScene("EditCC1.fxml");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error at selectEditTimekeepingScreen function at AdminTemplateController");
        }
    }
    public Employee getUser() {
        return user;
    }

}