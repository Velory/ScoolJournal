package sirotkina.sjournal.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Diary {
    public Diary() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("view/diary.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
