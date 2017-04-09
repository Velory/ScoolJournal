package sirotkina.sjournal.controller.shedule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sirotkina.sjournal.domain.Schedule;

import java.sql.Time;

public class ScheduleTableEdit extends ScheduleTableView {

    @Override
    @FXML
    protected void initialize() {
        day.setCellValueFactory(new PropertyValueFactory<>("weekDay"));
        day.setCellFactory(TextFieldTableCell.forTableColumn());
        day.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setWeekDay(event.getNewValue()));

        scoolClass.setCellValueFactory(new PropertyValueFactory<>("scoolClass"));
        scoolClass.setCellFactory(TextFieldTableCell.forTableColumn());
        scoolClass.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setScoolClass(event.getNewValue()));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));


        lessonTime.setCellValueFactory(new PropertyValueFactory<>("lessonTime"));
        lessonTime.setCellFactory(TextFieldTableCell.forTableColumn());
        lessonTime.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setLessonTime(Time.valueOf(event.getNewValue())));

        nameOfKurs.setCellValueFactory(new PropertyValueFactory<>("nameOfKurs"));
        nameOfKurs.setCellFactory(TextFieldTableCell.forTableColumn());
        nameOfKurs.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setNameOfKurs(event.getNewValue()));

        teacherOfLesson.setCellValueFactory(new PropertyValueFactory<>("teacherOfLesson"));
        teacherOfLesson.setCellFactory(TextFieldTableCell.forTableColumn());
        teacherOfLesson.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setTeacherOfLesson(event.getNewValue()));

        curentSchedule.setItems(getSheduleList());
    }

    @FXML
    private TextField newDay;

    @FXML
    private TextField newScoolClass;

    @FXML
    private TextField newLessonTime;

    @FXML
    private TextField newNameOfKurs;

    @FXML
    private TextField newTeacherOfLesson;

    @FXML
    private Button add;

    @FXML
    private Button save;


    @FXML
    private void onClick (ActionEvent event){

        getSheduleList().add(new Schedule(newDay.getText(), newScoolClass.getText(), null,
                Time.valueOf(newLessonTime.getText()), newNameOfKurs.getText(), newTeacherOfLesson.getText()));

        newDay.clear();
        newScoolClass.clear();
        newLessonTime.clear();
        newNameOfKurs.clear();
        newTeacherOfLesson.clear();
    }
}
