package sirotkina.sjournal.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateSchedule {

    public CreateSchedule() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("view/createSchedule.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
