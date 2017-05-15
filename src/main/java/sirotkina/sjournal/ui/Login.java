package sirotkina.sjournal.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Login extends Application{

    private static final Logger LOG = LoggerFactory.getLogger(Login.class);

    private static Stage stage;
    public static void main(String[] args) {
        Application.launch(args);

        LOG.debug("Login DEBUG");
        LOG.warn("Login WARNING");
        LOG.error("Login ERROR");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/sample.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }
}
