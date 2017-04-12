package sirotkina.sjournal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sirotkina.sjournal.ui.Login;
import sirotkina.sjournal.ui.MainMenu;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPassword;

    @FXML
    private TextField userEmail;

    @FXML
    private PasswordField userPassword;

    /*@FXML
    private Button login;*/

    public void onLogin() throws IOException {
        //check authorization
        new MainMenu();
        // close login?
        Login.getStage().close();

    }

}
