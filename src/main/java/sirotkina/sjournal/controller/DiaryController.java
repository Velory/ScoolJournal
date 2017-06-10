package sirotkina.sjournal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import sirotkina.sjournal.entity.Users;

public class DiaryController {

    @FXML
    private ComboBox<Users> selectStudentDiary;
    @FXML
    private ComboBox<Users> selectClassDiary;
    @FXML
    private Button firstDay;
    @FXML
    private Button secondDay;
    @FXML
    private Button thirdDay;
    @FXML
    private Button fourthDay;
    @FXML
    private Button fifthDay;
    @FXML
    private DatePicker period;


}
