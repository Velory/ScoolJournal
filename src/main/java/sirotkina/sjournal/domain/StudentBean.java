package sirotkina.sjournal.domain;

import sirotkina.sjournal.utils.myValidator.NotEmpty;
import sirotkina.sjournal.utils.myValidator.Pattern;

public class StudentBean {
    private final String PHONE_FIELD_PATTERN = "(^\\+[380]\\d*$)";
    private final String EMAIL_FIELD_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]" +
            "+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\" +
            "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])" +
            "?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9]" +
            "[0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b" +
            "\\x0c\\x0e-\\x7f])+)\\])";
    private final String AGE_FIELD_PATTERN = "(^[0-9][0-9])";

    @NotEmpty(message = "не выбрана роль")
    private String role;

    @NotEmpty(message = "не выбран класс")
    private String classAuth;

    @NotEmpty(message = "не корректно заполнено поле Фамилия")
    private String lastNameField;

    @NotEmpty(message = "не корректно заполнено поле Имя")
    private String firstNameField;

    @NotEmpty(message = "не корректно заполнено поле Отчество")
    private String midNameField;

    @Pattern(message = "неверно введен телефон", regexp = PHONE_FIELD_PATTERN)
    private String phoneField;

    @Pattern(message = "неверно введен возраст", regexp = AGE_FIELD_PATTERN)
    private String ageField;

    @Pattern(message = "неверно введен email", regexp = EMAIL_FIELD_PATTERN)
    private String emailField;

    @NotEmpty(message = "не корректно введен пароль")
    private String password;

    public StudentBean(String role, String classAuth, String lastNameField,
                       String firstNameField, String midNameField, String ageField,
                       String phoneField, String emailField, String password) {
        this.role = role;
        this.classAuth = classAuth;
        this.lastNameField = lastNameField;
        this.firstNameField = firstNameField;
        this.midNameField = midNameField;
        this.phoneField = phoneField;
        this.ageField = ageField;
        this.emailField = emailField;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentBean that = (StudentBean) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (classAuth != null ? !classAuth.equals(that.classAuth) : that.classAuth != null) return false;
        if (lastNameField != null ? !lastNameField.equals(that.lastNameField) : that.lastNameField != null)
            return false;
        if (firstNameField != null ? !firstNameField.equals(that.firstNameField) : that.firstNameField != null)
            return false;
        if (midNameField != null ? !midNameField.equals(that.midNameField) : that.midNameField != null) return false;
        if (phoneField != null ? !phoneField.equals(that.phoneField) : that.phoneField != null) return false;
        if (ageField != null ? !ageField.equals(that.ageField) : that.ageField != null) return false;
        if (emailField != null ? !emailField.equals(that.emailField) : that.emailField != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (classAuth != null ? classAuth.hashCode() : 0);
        result = 31 * result + (lastNameField != null ? lastNameField.hashCode() : 0);
        result = 31 * result + (firstNameField != null ? firstNameField.hashCode() : 0);
        result = 31 * result + (midNameField != null ? midNameField.hashCode() : 0);
        result = 31 * result + (phoneField != null ? phoneField.hashCode() : 0);
        result = 31 * result + (ageField != null ? ageField.hashCode() : 0);
        result = 31 * result + (emailField != null ? emailField.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
