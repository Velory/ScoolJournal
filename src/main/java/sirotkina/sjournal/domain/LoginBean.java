package sirotkina.sjournal.domain;

import sirotkina.sjournal.utils.myValidator.NotEmpty;

public class LoginBean {

    @NotEmpty(message = "Введите email")
    private String userEmail;
    @NotEmpty(message = "Введите пароль")
    private String userPassword;

    public LoginBean(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
