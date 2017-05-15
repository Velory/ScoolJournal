package sirotkina.sjournal.domain;

import sirotkina.sjournal.utils.myValidator.NotEmpty;
import sirotkina.sjournal.utils.myValidator.Pattern;

public class TeacherBean {
    private final String PHONE_FIELD_PATTERN = "(^\\+[380]\\d*$)";
    private final String EMAIL_FIELD_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]" +
                                    "+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\" +
                                    "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])" +
                                    "?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9]" +
                                    "[0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:" +
                                    "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b" +
                                    "\\x0c\\x0e-\\x7f])+)\\])";

    @NotEmpty(message = "не выбрана роль")
    private String role;

    @NotEmpty(message = "не выбран класс")
    private String classAuth;

    @NotEmpty(message = "не выбран курс")
    private String kursAuth;

    @NotEmpty(message = "не корректно заполнено поле Фамилия")
    private String lastNameField;

    @NotEmpty(message = "не корректно заполнено поле Имя")
    private String firstNameField;

    @NotEmpty(message = "не корректно заполнено поле Отчество")
    private String midNameField;

    @Pattern(message = "неверно введен телефон", regexp = PHONE_FIELD_PATTERN)
    private String phoneField;

    @Pattern(message = "неверно введен email", regexp = EMAIL_FIELD_PATTERN)
    private String emailField;

    @NotEmpty(message = "не корректно введен пароль")
    private String password;

    public TeacherBean(String role, String classAuth, String kursAuth, String lastNameField,
                       String firstNameField, String midNameField, String phoneField,
                       String emailField, String password) {
        this.role = role;
        this.classAuth = classAuth;
        this.kursAuth = kursAuth;
        this.lastNameField = lastNameField;
        this.firstNameField = firstNameField;
        this.midNameField = midNameField;
        this.phoneField = phoneField;
        this.emailField = emailField;
        this.password = password;
    }

}
