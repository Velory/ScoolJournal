package sirotkina.sjournal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sirotkina.sjournal.domain.UsersBean;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.*;
import sirotkina.sjournal.ui.Authorization;
import sirotkina.sjournal.ui.Login;
import sirotkina.sjournal.utils.myValidator.Validator;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import static sirotkina.sjournal.utils.ControllersUtils.*;
import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.usersDAO;

public class AuthorizationController {

    private Validator validator;

    @FXML
    private ComboBox<Role> role;
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
    private DatePicker birthday;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField password;

    @FXML
    private Label registrationMsg;
    @FXML
    private Label classLbl;
    @FXML
    private Label kursLbl;

    public void initialize() throws IOException {
        birthday.setConverter(dateConverter());
        birthday.setEditable(false);

        role.getItems().addAll(getRoleList());
        role.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> onRoleAction());

        classAuth.getItems().addAll(getClassList());
        classAuth.setConverter(classConverter());

        kursAuth.getItems().addAll(getKursList());
        kursAuth.setConverter(kursConverter());

        nodeIsActive(false, classAuth, classLbl, kursAuth, kursLbl);
    }

    public void onRoleAction() {
        if (role.getValue().getRole().equals(sirotkina.sjournal.utils.Role.ADMIN.getValue()) ||
                role.getValue().getRole().equals(sirotkina.sjournal.utils.Role.PARENT.getValue())){
            nodeIsActive(false, classAuth, classLbl, kursAuth, kursLbl);
            classAuth.setValue(defaultClassFromDB());
            kursAuth.setValue(defaultkursFromDB());
        }
        if (role.getValue().getRole().equals(sirotkina.sjournal.utils.Role.STUDENT.getValue())) {
            nodeIsActive(true, classAuth, classLbl);
            nodeIsActive(false, kursLbl, kursAuth);
            kursAuth.setValue(defaultkursFromDB());
        }
        if (role.getValue().getRole().equals(sirotkina.sjournal.utils.Role.TEACHER.getValue())) {
            nodeIsActive(true, kursAuth, kursLbl);
            nodeIsActive(false, classLbl, classAuth);
            classAuth.setValue(defaultClassFromDB());
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
                clearingFields();
                registrationMsg.setText("Регистрация прошла успешно");
            }
        } else {
            registrationMsg.setText(String.valueOf(messages));
        }
    }

    public void onEnter() {
        Login.getStage().show();
        Authorization.getStage().close();
    }

    private Validator getValidator(java.lang.Class cl) {
        return new Validator(cl);
    }

    private UsersBean getUsersBean() {
        return new UsersBean(firstNameField.getText(), midNameField.getText(), lastNameField.getText(),
                dateConverter().toString(birthday.getValue()), phoneField.getText(), emailField.getText(),
                classConverter().toString(classAuth.getValue()),
                kursConverter().toString(kursAuth.getValue()),
                password.getText(), roleConverter().toString(role.getValue()));
    }

    private Users getNewUser() {
        return new Users(null,
                firstNameField.getText(),
                midNameField.getText(),
                lastNameField.getText(),
                Date.valueOf(birthday.getValue()),
                phoneField.getText(),
                emailField.getText(),
                classAuth.getValue(),
                kursAuth.getValue(),
                password.getText(),
                role.getValue());
    }

    private void clearingFields() {
        firstNameField.clear();
        midNameField.clear();
        lastNameField.clear();
        phoneField.clear();
        emailField.clear();
        password.clear();
    }
}
