package sirotkina.sjournal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AuthorizationController {

    private ObservableList<String> roleList;

    @FXML
    private ComboBox<String> role;

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
    private Button registration;

    public void initialize(){
        roleList = FXCollections.observableArrayList("Ученик", "Учитель");
        role.getItems().addAll(roleList);
        role.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> onRoleAction());
        ageField.setDisable(true);
    }

    public void onRoleAction(){
        if (role.valueProperty().equals(roleList.get(0))){
            ageField.setDisable(false);
        }
    }

    public void onRegistration(){

    }

}
