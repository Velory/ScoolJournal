package sirotkina.sjournal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import sirotkina.sjournal.ui.Authorization;
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

    @FXML
    private Label auth;

    public void onLogin() throws IOException {
        //check authorization
        new MainMenu();
        Login.getStage().close();
    }

    public void onClickAuthorization() throws IOException {
        new Authorization();
        Login.getStage().close();
    }

    public void onMovedAuthorization() {
        auth.setTextFill(Paint.valueOf("#a3748a"));
    }

    public void onExitedAuthorization() {
        auth.setTextFill(Paint.valueOf("#fa3242"));
    }

}
