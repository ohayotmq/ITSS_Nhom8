package project.itss.group8.itss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUIStarter extends Application {

    private  Stage mainStage;
//  public static Stage MAIN_STAGE;
    private static final List<Stage> secondaryStages = new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(GUIStarter.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();


        mainStage = stage;
        mainStage.setOnCloseRequest(ev -> {
            for (var secondaryStage : secondaryStages) {
                secondaryStage.close();
            }
        });
    }
    public static void main(String[] args) {
        launch();
    }
}