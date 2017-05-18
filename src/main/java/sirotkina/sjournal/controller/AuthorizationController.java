package sirotkina.sjournal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sirotkina.sjournal.domain.StudentBean;
import sirotkina.sjournal.domain.TeacherBean;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.entity.Students;
import sirotkina.sjournal.entity.Teachers;
import sirotkina.sjournal.ui.Authorization;
import sirotkina.sjournal.ui.Login;
import sirotkina.sjournal.utils.myValidator.Validator;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static sirotkina.sjournal.utils.ControllersUtils.*;
import static sirotkina.sjournal.utils.ConvertersUtils.classConverter;
import static sirotkina.sjournal.utils.ConvertersUtils.kursConverter;
import static sirotkina.sjournal.utils.DatabaseUtils.studentsDAO;
import static sirotkina.sjournal.utils.DatabaseUtils.teachersDAO;

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
        if (role.getValue().equals("Ученик")) {
            ageKursLbl.setText("Возраст");
            nodeIsActive(true, ageField, ageKursLbl);
            nodeIsActive(false, kursAuth);
        }
        if (role.getValue().equals("Учитель")) {
            ageKursLbl.setText("Выберите предмет");
            nodeIsActive(true, kursAuth, ageKursLbl);
            nodeIsActive(false, ageField);
        }
    }

    public void onRegistration() {

        if (role.getValue().equals("Ученик")) {
            validator = getValidator(StudentBean.class);
            Set<String> messages = validator.validate(getStudentBean());
            if (messages.isEmpty()) {
                Students students = getNewStudent();
                List<Students> studentsList = studentsDAO().getAll();
                if (!studentsList.contains(students)) {
                    studentsDAO().save(students);
                    clearingFields();
                    registrationMsg.setText("Регистрация прошла успешно");
                }
            } else {
                registrationMsg.setText(String.valueOf(messages));
            }
        }

        if (role.getValue().equals("Учитель")) {
            validator = getValidator(TeacherBean.class);
            Set<String> messages = validator.validate(getTeacherBean());
            if (messages.isEmpty()) {
                Teachers teachers = getNewTeacher();
                List<Teachers> teachersList = teachersDAO().getAll();
                if (!teachersList.contains(teachers)) {
                    teachersDAO().save(teachers);
                    clearingFields();
                    registrationMsg.setText("Регистрация прошла успешно");
                }
            } else {
                registrationMsg.setText(String.valueOf(messages));
            }
        }
    }

    public void onEnter() {
        new Login();
        Authorization.getStage().close();
    }

    private Validator getValidator(java.lang.Class cl) {
        return new Validator(cl);
    }

    private TeacherBean getTeacherBean() {
        return new TeacherBean(role.getValue(), classConverter().toString(classAuth.getValue()),
                kursConverter().toString(kursAuth.getValue()),
                lastNameField.getText(), firstNameField.getText(), midNameField.getText(),
                phoneField.getText(), emailField.getText(), password.getText());
    }

    private StudentBean getStudentBean() {
        return new StudentBean(role.getValue(),
                classConverter().toString(classAuth.getValue()),
                lastNameField.getText(), firstNameField.getText(), midNameField.getText(),
                ageField.getText(), phoneField.getText(), emailField.getText(), password.getText());
    }

    private Students getNewStudent() {
        return new Students(null,
                firstNameField.getText(),
                midNameField.getText(),
                lastNameField.getText(),
                Integer.valueOf(ageField.getText()),
                phoneField.getText(),
                emailField.getText(),
                classConverter().checkClassInDB(classAuth.getValue()),
                password.getText());
    }

    private Teachers getNewTeacher() {
        return new Teachers(null,
                firstNameField.getText(),
                midNameField.getText(),
                lastNameField.getText(),
                phoneField.getText(),
                emailField.getText(),
                kursConverter().checkKursInDB(kursAuth.getValue()),
                classConverter().checkClassInDB(classAuth.getValue()),
                password.getText());
    }

    private void clearingFields() {
        firstNameField.clear();
        midNameField.clear();
        lastNameField.clear();
        ageField.clear();
        phoneField.clear();
        emailField.clear();
        password.clear();
    }
}
