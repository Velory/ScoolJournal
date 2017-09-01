package sirotkina.sjournal.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Authorization {
    private static Stage stage;

    public Authorization() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/authorization.fxml"));
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }
}
