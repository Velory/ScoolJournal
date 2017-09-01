package sirotkina.sjournal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sirotkina.sjournal.entity.Users;

import static sirotkina.sjournal.utils.ControllersUtils.*;

public class PersonalProfileController {

    private Users currentUser;
    @FXML
    private Label nameLbl;
    @FXML
    private Label emailLbl;
    @FXML
    private Label phoneLbl;
    @FXML
    private Label birthdayLbl;

    public void initialize(){
        currentUser = getCurrentUser();
        nameLbl.setText(currentUser.getFirstName() + " " +
                currentUser.getMidName() + " " + getCurrentUser().getLastName());
        emailLbl.setText(currentUser.getEmail());
        phoneLbl.setText(currentUser.getPhone());
        birthdayLbl.setText(currentUser.getBirthday().toString());
    }
}
