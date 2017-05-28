package sirotkina.sjournal.domain;

import sirotkina.sjournal.utils.myValidator.NotEmpty;
import sirotkina.sjournal.utils.myValidator.Pattern;

public class UsersBean {
    private final String PHONE_FIELD_PATTERN = "(^\\+380\\d*$)";
    private final String EMAIL_FIELD_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]" +
            "+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\" +
            "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])" +
            "?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9]" +
            "[0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b" +
            "\\x0c\\x0e-\\x7f])+)\\])";
    private final String AGE_FIELD_PATTERN = "(^[0-9]{1,2})";

    @NotEmpty(message = "не корректно заполнено поле Имя")
    private String firstNameField;

    @NotEmpty(message = "не корректно заполнено поле Отчество")
    private String midNameField;

    @NotEmpty(message = "не корректно заполнено поле Фамилия")
    private String lastNameField;

    @Pattern(message = "неверно введен возраст", regexp = AGE_FIELD_PATTERN)
    private String ageField;

    @Pattern(message = "неверно введен телефон", regexp = PHONE_FIELD_PATTERN)
    private String phoneField;

    @Pattern(message = "неверно введен email", regexp = EMAIL_FIELD_PATTERN)
    private String emailField;

    @NotEmpty(message = "не выбран класс")
    private String classAuth;

    @NotEmpty(message = "не выбран курс")
    private String kursAuth;

    @NotEmpty(message = "не корректно введен пароль")
    private String password;

    @NotEmpty(message = "не выбрана роль")
    private String role;

    public UsersBean(String firstNameField, String midNameField, String lastNameField, String ageField, String phoneField, String emailField, String classAuth, String kursAuth, String password, String role) {
        this.firstNameField = firstNameField;
        this.midNameField = midNameField;
        this.lastNameField = lastNameField;
        this.ageField = ageField;
        this.phoneField = phoneField;
        this.emailField = emailField;
        this.classAuth = classAuth;
        this.kursAuth = kursAuth;
        this.password = password;
        this.role = role;
    }


}
