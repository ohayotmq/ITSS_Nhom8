package project.itss.group8.itss.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import project.itss.group8.itss.model.Form;
import project.itss.group8.itss.helper.IUpdateService;
import project.itss.group8.itss.helper.Impl.UpdateServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class UpdateController extends WorkspaceController{

    IUpdateService service = new UpdateServiceImpl();

    @FXML
    private Label idLabel;

    @FXML
    private Label oldTimestampLabel;

    @FXML
    private Label newTimestampLabel;

    @FXML
    private Label oldDevice;

    @FXML
    private Label newDevice;

    @FXML
    private TextField timestampTextField;

    @FXML
    private TextField deviceTextField;

    @FXML
    private Button rejectButton;

    @FXML
    private Button acceptButton;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static Form form;

    public void initialize() {
        idLabel.setText(Integer.toString(form.getIdnv()));
        oldTimestampLabel.setText(form.getOldT().format(formatter));
        newTimestampLabel.setText(form.getNewT().format(formatter));
        oldDevice.setText(String.valueOf(form.getOldDevice()));
        newDevice.setText(String.valueOf(form.getNewDevice()));
        timestampTextField.setText(form.getNewT().format(formatter));
        deviceTextField.setText(String.valueOf(form.getNewDevice()));

        return;
    }
    public static void setForm(Form form){
        UpdateController.form = form;
    }
    @FXML
    private void handleAcceptButton() {
        form.setNewT(LocalDateTime.parse(timestampTextField.getText(),formatter));
        form.setNewDevice(Integer.parseInt(deviceTextField.getText()));
        service.acceptChangeInfor(form);
        try {
            changeWorkspace("/project/itss/group8/itss/view/EditCC1.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;// Xử lý sự kiện khi nhấn nút "Từ chối"
    }

    @FXML
    private void handleRejectButton() {
        service.rejectChangeInfor(form.getIdform());
    }
}
