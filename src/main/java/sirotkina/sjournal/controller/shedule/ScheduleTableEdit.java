package sirotkina.sjournal.controller.shedule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import sirotkina.sjournal.domain.Schedule;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Lesson;
import sirotkina.sjournal.utils.ClassStringConverter;

import java.sql.Date;
import java.sql.Time;

import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class ScheduleTableEdit extends ScheduleTableView {

    @Override
    @FXML
    protected void initialize() {
        super.initialize();

        day.setCellFactory(TextFieldTableCell.forTableColumn());
        day.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setWeekDay(event.getNewValue()));

        scoolClass.setCellFactory(TextFieldTableCell.forTableColumn());
        scoolClass.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setScoolClass(event.getNewValue()));


        lessonTime.setCellFactory(TextFieldTableCell.forTableColumn());
        lessonTime.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setLessonTime(event.getNewValue()));

        nameOfKurs.setCellFactory(TextFieldTableCell.forTableColumn());
        nameOfKurs.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setNameOfKurs(event.getNewValue()));

        teacherOfLesson.setCellFactory(TextFieldTableCell.forTableColumn());
        teacherOfLesson.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setTeacherOfLesson(event.getNewValue()));
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
    private void onAddClick(){
        getTableElements().add(new Schedule(newDay.getText(), newScoolClass.getText(), null,
                newLessonTime.getText(), newNameOfKurs.getText(), newTeacherOfLesson.getText()));
        newDay.clear();
        newScoolClass.clear();
        newLessonTime.clear();
        newNameOfKurs.clear();
        newTeacherOfLesson.clear();
    }

    public ObservableList<Class> getClassList (){
        return FXCollections.observableList(classDAO().getAll());
    }

    public void valueChanged (ComboBox<Class> list){
        Class cl = list.getValue();
        String name = cl.getNum() + "-" + cl.getLetter();
        //add code to change the table
    }

    private void onSaveClick(){
        /*ClassStringConverter convertClass = new ClassStringConverter();
        for (Schedule el: getTableElements()) {
            Lesson lesson = new Lesson(null, Date.valueOf(el.getWeekDay()),
                    Time.valueOf(el.getLessonTime()),null,
                    convertClass.checkClassInDB(convertClass.fromString(el.getScoolClass())),
                    el.getTeacherOfLesson(), el.getNameOfKurs())
            lessonDAO().save(lesson);*/

        }
}

