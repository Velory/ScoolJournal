package sirotkina.sjournal.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CurrentSchedule {

    public CurrentSchedule() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("view/currentSchedule.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
