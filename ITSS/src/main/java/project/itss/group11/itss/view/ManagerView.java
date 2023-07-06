package project.itss.group11.itss.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerView extends TemplateView{

    public ManagerView(Stage stage) {
        super(stage);
    }

    public void init() {
        FXMLLoader rootFxmlLoader = new FXMLLoader(getClass().getResource("template.fxml"));
        rootFxmlLoader.setController(templateController);
        Parent root = null;
        try {
            root = rootFxmlLoader.load();
        } catch (IOException e) {
            logger.error("Error in init: ", e);
        }
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("template.css").toExternalForm());
    }

    @Override
    public void show() throws IOException {
        init();
        showHome();
        // init workspace

        Button importViewStaffButton = createOptionButton("Xem chấm công");
        importViewStaffButton.setOnMouseClicked(event -> {
            try {
                addToWorkspace("XemTQNV.fxml");
            } catch (IOException e) {
                logger.error("Error in Employee view", e);
            }
        });

        Button importViewUnitButton = createOptionButton("Xem chấm công đơn vị");
        importViewUnitButton.setOnMouseClicked(event -> {
            try {
                addToWorkspace("/project/itss/group11/itss/view/manager/ViewEmployeeUnit.fxml");
            } catch (IOException e) {
                logger.error("Error in Employee view", e);
            }
        });
        addOptionButton(importViewStaffButton);
        addOptionButton(importViewUnitButton);

    }
}
