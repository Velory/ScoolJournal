package sirotkina.sjournal.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {
    public MainMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/mainMenu.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
