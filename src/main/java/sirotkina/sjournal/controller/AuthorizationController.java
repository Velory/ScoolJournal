package sirotkina.sjournal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Students;
import sirotkina.sjournal.entity.Teachers;
import sirotkina.sjournal.utils.ClassStringConverter;

import java.util.ArrayList;
import java.util.List;

import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class AuthorizationController {

    private ObservableList<String> roleList;
    private ObservableList<Class> classList;
    //private ClassStringConverter classConverter;
    @FXML private ComboBox<String> role;
    @FXML private ComboBox<Class> classAuth;
    @FXML private TextField lastNameField;
    @FXML private TextField firstNameField;
    @FXML private TextField midNameField;
    @FXML private TextField phoneField;
    @FXML private TextField ageField;
    @FXML private TextField emailField;
    @FXML private PasswordField password;
    @FXML private Button registration;
    @FXML private Label registrationMsg;
    @FXML private Label ageLbl;

    public void initialize(){
        roleList = FXCollections.observableArrayList("Ученик", "Учитель");
        role.getItems().addAll(roleList);
        role.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> onRoleAction());

        ageField.setDisable(true);
        ageField.setVisible(false);
        ageLbl.setVisible(false);

        classAuth.getItems().addAll(getClassList());
        classAuth.setConverter(getClassConverter());
    }

    public void onRoleAction(){
        if (role.getValue().equals(roleList.get(0))){
            ageField.setDisable(false);
            ageField.setVisible(true);
            ageLbl.setVisible(true);
        } else {
            ageField.setDisable(true);
            ageField.setVisible(false);
            ageLbl.setVisible(false);
        }
    }

    public void onRegistration(){
        if (role.getValue().equals(roleList.get(0))){
            Students students = getNewStudent();
            List<Students> studentsList = studentsDAO().getAll();
            if (!studentsList.contains(students)){
                studentsDAO().save(students);
                firstNameField.clear();
                midNameField.clear();
                lastNameField.clear();
                ageField.clear();
                phoneField.clear();
                emailField.clear();
                password.clear();
                registrationMsg.setText("Регистрация прошла успешно");
            } else {
                registrationMsg.setText("Пользователь с таким именем уже существует");
            }
        }
    }

    private Students getNewStudent(){
        return new Students(null,
                firstNameField.getText(),
                midNameField.getText(),
                lastNameField.getText(),
                Integer.valueOf(ageField.getText()),
                phoneField.getText(),
                emailField.getText(),
                getClassConverter().checkClassInDB(classAuth.getValue()));
    }

    private Teachers getNewTeacher(){
        return new Teachers(null,
                firstNameField.getText(),
                midNameField.getText(),
                lastNameField.getText(),
                phoneField.getText(),
                emailField.getText(),
                null,
                null);
    }

    public ObservableList<Class> getClassList(){
        return FXCollections.observableList(classDAO().getAll());
    }

    public ObservableList<String> getRoleList() {
        return roleList;
    }

    public ClassStringConverter getClassConverter() {
        return new ClassStringConverter();
    }
}
