package project.itss.group11.itss.controller;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import project.itss.group11.itss.HelloApplication;
import project.itss.group11.itss.Until.ConnectionPool;
import project.itss.group11.itss.Until.Constant;
import project.itss.group11.itss.model.Employee;
import project.itss.group11.itss.service.Impl.LoginServiceImpl;
import project.itss.group11.itss.view.ManagerView;
import project.itss.group11.itss.view.QLNSView;
import project.itss.group11.itss.service.LoginService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import project.itss.group11.itss.view.EmployeeView;

// no longer need to extends BaseController
public class LoginController extends BaseController implements Initializable{

    private static LoginService loginService = new LoginServiceImpl();

    @FXML
    private Label status;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private Employee user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Thiết lập giá trị ban đầu
        //System.out.println("Load Oke");
        Constant.pool = ConnectionPool.getInstance("etc/database.config");
        Text text = new Text("Tài khoản hoặc mật khẩu sai" );
        text.setFill(Color.RED);
        status.setGraphic(text);
        status.setVisible(false);



        //logger.info("Start load all conf");
    }


    @FXML
    public void handleLogin(Event event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String username = usernameField.getText();
        String password = passwordField.getText();

        logger.info("Start login user: " + username + " , pass: " + password);

        try {
            if (loginService.checkLogin(username, password)) {
                logger.info("Login Success");
                Constant.employee = loginService.getUserInfor(Integer.parseInt(username));
                if (Constant.employee.getRole() == 3) {
                    //changeScene("staff/UserTemplate.fxml");
                    EmployeeView employeeView = new EmployeeView(stage);
                    employeeView.show();
                } else if (Constant.employee.getRole() == 1) {
                    QLNSView qlnsView = new QLNSView(stage);
                    qlnsView.show();
                } else if (Constant.employee.getRole() == 2) {
                    ManagerView managerView = new ManagerView(stage);
                    managerView.show();
                }
            } else {
                loginFail();
            }
        }catch (Exception e){
            logger.error("Error in handleLogin ",e);
            loginFail();
        }

        // Xử lý logic đăng nhập ở đây
        // ...
    }
    private void loginFail(){
        logger.info("Login Fail");
        status.setVisible(true);
        passwordField.setText(null);
    }

}