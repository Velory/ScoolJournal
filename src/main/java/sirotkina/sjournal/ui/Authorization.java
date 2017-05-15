package sirotkina.sjournal.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Authorization {
    private static Stage stage;
    public Authorization() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("view/authorization.fxml"));
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }
}
