package sirotkina.sjournal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import sirotkina.sjournal.domain.LoginBean;
import sirotkina.sjournal.entity.Users;
import sirotkina.sjournal.ui.Authorization;
import sirotkina.sjournal.ui.Login;
import sirotkina.sjournal.ui.MainMenu;
import sirotkina.sjournal.utils.myValidator.Validator;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static sirotkina.sjournal.utils.ControllersUtils.setCurrentUser;
import static sirotkina.sjournal.utils.DatabaseUtils.usersDAO;

public class LoginController {

    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Label auth;
    @FXML
    private Label msgLbl;

    private Validator validator;

    public void onLogin() throws IOException {
        validator = new Validator(LoginBean.class);
        Set<String> messages = validator.validate(new LoginBean(userEmail.getText(), userPassword.getText()));
        if (messages.isEmpty()){
            if(isAuthorizationValid()){
                new MainMenu();
                Login.getStage().close();
            } else {
                msgLbl.setText("Неверно введен логин и пароль");
            }
        } else {
            msgLbl.setText(String.valueOf(messages));
        }
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

    private boolean isAuthorizationValid(){
        List<Users> users = usersDAO().getAll();
        String email = userEmail.getText();
        String password = userPassword.getText();
        for (Users user: users){
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())){
                setCurrentUser(user);
                return true;
            }
        }
        return false;
    }

}
