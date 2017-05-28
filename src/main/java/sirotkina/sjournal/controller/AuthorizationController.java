package sirotkina.sjournal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sirotkina.sjournal.domain.UsersBean;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.entity.Users;
import sirotkina.sjournal.ui.Authorization;
import sirotkina.sjournal.utils.Role;
import sirotkina.sjournal.utils.myValidator.Validator;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static sirotkina.sjournal.utils.ControllersUtils.*;
import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.usersDAO;

public class AuthorizationController {

    private Validator validator;

    @FXML
    private ComboBox<String> role;
    @FXML
    private ComboBox<Class> classAuth;
    @FXML
    private ComboBox<Kurs> kursAuth;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField midNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField password;

    @FXML
    private Label registrationMsg;
    @FXML
    private Label ageKursLbl;

    public void initialize() throws IOException {

        role.getItems().addAll(getRoleList());
        role.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> onRoleAction());

        classAuth.getItems().addAll(getClassList());
        classAuth.setConverter(classConverter());

        kursAuth.getItems().addAll(getKursList());
        kursAuth.setConverter(kursConverter());

        nodeIsActive(false, kursAuth, ageField, ageKursLbl);
    }

    public void onRoleAction() {
        if (role.getValue().equals(Role.STUDENT.getValue())) {
            ageKursLbl.setText("Возраст");
            nodeIsActive(true, ageField, ageKursLbl);
            nodeIsActive(false, kursAuth);
            kursAuth.setValue(null);
        }
        if (role.getValue().equals(Role.TEACHER.getValue())) {
            ageKursLbl.setText("Выберите предмет");
            nodeIsActive(true, kursAuth, ageKursLbl);
            nodeIsActive(false, ageField);
            ageField.setText("0");
        }
    }

    public void onRegistration() {
        validator = getValidator(UsersBean.class);
        Set<String> messages = validator.validate(getUsersBean());
        if (messages.isEmpty()) {
            Users users = getNewUser();
            List<Users> usersList = usersDAO().getAll();
            if (!usersList.contains(users)) {
                usersDAO().save(users);
                //clearingFields();
                registrationMsg.setText("Регистрация прошла успешно");
            }
        } else {
            registrationMsg.setText(String.valueOf(messages));
        }
    }

    public void onEnter() {
        // Login.main(null);
        Authorization.getStage().close();
    }

    private Validator getValidator(java.lang.Class cl) {
        return new Validator(cl);
    }

    private UsersBean getUsersBean() {
        return new UsersBean(firstNameField.getText(), midNameField.getText(), lastNameField.getText(),
                ageField.getText(), phoneField.getText(), emailField.getText(),
                classConverter().toString(classAuth.getValue()),
                kursConverter().toString(kursAuth.getValue()),
                password.getText(), role.getValue());
    }

    private Users getNewUser() {
        return new Users(null,
                firstNameField.getText(),
                midNameField.getText(),
                lastNameField.getText(),
                Integer.valueOf(ageField.getText()),
                phoneField.getText(),
                emailField.getText(),
                classAuth.getValue(),
                kursAuth.getValue(),
                password.getText(),
                roleConverter().checkRoleInDB(roleConverter().fromString(role.getValue())));
    }

    /*private void clearingFields() {
        firstNameField.clear();
        midNameField.clear();
        lastNameField.clear();
        ageField.clear();
        phoneField.clear();
        emailField.clear();
        password.clear();
    }*/
}
